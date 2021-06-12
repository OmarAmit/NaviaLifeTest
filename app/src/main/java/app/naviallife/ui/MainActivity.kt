package app.naviallife.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import app.naviallife.util.Utils
import app.naviallife.R
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import app.naviallife.adapter.WeekAdapter
import app.naviallife.model.WeekDiet
import app.naviallife.util.Constant.BASE_URL
import app.naviallife.util.MyProgressDialog
import app.naviallife.work.NotifyWork
import app.naviallife.work.NotifyWork.Companion.NOTIFICATION_ID
import app.naviallife.work.NotifyWork.Companion.NOTIFICATION_WORK
import java.util.*
import java.util.concurrent.TimeUnit.MILLISECONDS

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: WeekAdapter
    private val EARLY_TIMER_IN_MILLIS: Long = 5 * 60 * 1000
    private var requestQueue: RequestQueue? = null
    private var recyclerView: RecyclerView? = null
    var result = ArrayList<WeekDiet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestQueue = Volley.newRequestQueue(this)
        setRecyclerView()
        MyProgressDialog.showPDialog(this)
        getdatafromAPI()
    }

    private fun setRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        adapter = WeekAdapter(result)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.adapter = adapter
    }

    private fun getdatafromAPI() {

        val request =
            JsonObjectRequest(Request.Method.GET, BASE_URL, null, Response.Listener { response ->
                try {
                    Log.e("response", response.toString())
                    MyProgressDialog.hidePDialog()
                    val jsonobject = response.getJSONObject("week_diet_data")
                    val monday =
                        if (jsonobject.isNull("monday")) null else jsonobject.getJSONArray("monday")
                    val tuesday =
                        if (jsonobject.isNull("tuesday")) null else jsonobject.getJSONArray("tuesday")
                    val wednesday =
                        if (jsonobject.isNull("wednesday")) null else jsonobject.getJSONArray("wednesday")
                    val thursday =
                        if (jsonobject.isNull("thursday")) null else jsonobject.getJSONArray("thursday")
                    val friday =
                        if (jsonobject.isNull("friday")) null else jsonobject.getJSONArray("friday")
                    val saturday =
                        if (jsonobject.isNull("saturday")) null else jsonobject.getJSONArray("saturday")
                    val sunday =
                        if (jsonobject.isNull("sunday")) null else jsonobject.getJSONArray("sunday")
                    Log.e("monday", monday.toString())


                    if (monday != null) {
                        setdatatolist(monday, "monday")
                    }
                    if (tuesday != null) {
                        setdatatolist(tuesday, "tuesday")
                    }
                    if (wednesday != null) {
                        setdatatolist(wednesday, "wednesday")
                    }
                    if (thursday != null) {
                        setdatatolist(thursday, "thursday")
                    }
                    if (friday != null) {
                        setdatatolist(friday, "friday")
                    }
                    if (saturday != null) {
                        setdatatolist(saturday, "saturday")
                    }
                    if (sunday != null) {
                        setdatatolist(sunday, "sunday")
                    }
                    adapter.notifyDataSetChanged()

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }

    private fun setdatatolist(dietArray: JSONArray, s: String) {
        for (i in 0 until dietArray.length()) {
            val day_data = dietArray.getJSONObject(i)
            val food = day_data.getString("food")
            val meal_time = day_data.getString("meal_time")

            var diet: WeekDiet = WeekDiet(food, meal_time, s)
            result.add(diet)
            if (s.equals("monday")) {
                val data = Data.Builder().putInt(NOTIFICATION_ID, 0).build()

                val reminderTimeDelay =
                    Utils.getMondayDateTime(
                        meal_time,
                        EARLY_TIMER_IN_MILLIS
                    )
                scheduleNotification(reminderTimeDelay, data)
            }
            if (s.equals("tuesday")) {
                val data = Data.Builder().putInt(NOTIFICATION_ID, 0).build()
                val reminderTimeDelay =
                    Utils.getTuesdayDateTime(
                        meal_time,
                        EARLY_TIMER_IN_MILLIS
                    )
                scheduleNotification(reminderTimeDelay, data)
            }
            if (s.equals("wednesday")) {
                val data = Data.Builder().putInt(NOTIFICATION_ID, 0).build()
                val reminderTimeDelay =
                    Utils.getWednesdayDateTime(
                        meal_time,
                        EARLY_TIMER_IN_MILLIS
                    )
                scheduleNotification(reminderTimeDelay, data)
            }
            if (s.equals("thursday")) {
                val data = Data.Builder().putInt(NOTIFICATION_ID, 0).build()
                val reminderTimeDelay =
                    Utils.getThursdayDateTime(
                        meal_time,
                        EARLY_TIMER_IN_MILLIS
                    )
                scheduleNotification(reminderTimeDelay, data)
            }
            if (s.equals("friday")) {
                val data = Data.Builder().putInt(NOTIFICATION_ID, 0).build()
                val reminderTimeDelay =
                    Utils.getFridayDateTime(
                        meal_time,
                        EARLY_TIMER_IN_MILLIS
                    )
                scheduleNotification(reminderTimeDelay, data)
            }
            if (s.equals("saturday")) {
                val data = Data.Builder().putInt(NOTIFICATION_ID, 0).build()
                val reminderTimeDelay =
                    Utils.getSaturdayDateTime(
                        meal_time,
                        EARLY_TIMER_IN_MILLIS
                    )
                scheduleNotification(reminderTimeDelay, data)
            }
            if (s.equals("sunday")) {
                val data = Data.Builder().putInt(NOTIFICATION_ID, 0).build()
                val reminderTimeDelay =
                    Utils.getSundayDateTime(
                        meal_time,
                        EARLY_TIMER_IN_MILLIS
                    )
                scheduleNotification(reminderTimeDelay, data)
            }
        }
    }

    private fun scheduleNotification(delay: Long, data: Data) {
        val notificationWork = OneTimeWorkRequest.Builder(NotifyWork::class.java)
            .setInitialDelay(delay, MILLISECONDS).setInputData(data).build()

        val instanceWorkManager = WorkManager.getInstance(this)
        instanceWorkManager.beginUniqueWork(
            NOTIFICATION_WORK,
            ExistingWorkPolicy.APPEND,
            notificationWork
        ).enqueue()

    }
}
