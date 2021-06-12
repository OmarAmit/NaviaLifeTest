package app.naviallife.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.naviallife.R
import app.naviallife.model.WeekDiet
import java.util.ArrayList

class WeekAdapter(private var items: ArrayList<WeekDiet>) :
    RecyclerView.Adapter<WeekAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var weekdiet = items[position]
        holder?.txtName?.text = weekdiet.food
        holder?.txtComment?.text = weekdiet.meel_time
        holder?.txtDay?.text = weekdiet.day
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.dietlist, parent, false)

        return ViewHolder(itemView)
    }


    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var txtName: TextView? = null
        var txtComment: TextView? = null
        var txtDay: TextView? = null

        init {
            this.txtName = row?.findViewById<TextView>(R.id.txtName)
            this.txtComment = row?.findViewById<TextView>(R.id.txtmeal_time)
            this.txtDay = row?.findViewById<TextView>(R.id.txtDay)

        }
    }
}