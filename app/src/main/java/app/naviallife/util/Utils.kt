package app.naviallife.util

import android.util.Log
import java.util.*


object Utils {

    fun getMondayDateTime(mealTime: String, earlyInMillis: Long): Long {
        val today = Calendar.getInstance()
        val monday = Calendar.getInstance()
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        val time = getTimeHrMin(mealTime)
        val hour = time[0]
        val minute = time[1]
        monday.set(Calendar.HOUR_OF_DAY, hour.toInt())
        monday.set(Calendar.MINUTE, minute.toInt())
        if (monday.time.before(today.time)) {
            monday.add(Calendar.WEEK_OF_YEAR, 1)
        }
        Log.e("monday", monday.time.toString())

        return monday.timeInMillis - System.currentTimeMillis() - earlyInMillis
    }

    fun getTuesdayDateTime(mealTime: String, earlyInMillis: Long): Long {
        val today = Calendar.getInstance()
        val tuesday = Calendar.getInstance()
        tuesday.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY)
        val time = getTimeHrMin(mealTime)
        val hour = time[0]
        val minute = time[1]
        tuesday.set(Calendar.HOUR_OF_DAY, hour.toInt())
        tuesday.set(Calendar.MINUTE, minute.toInt())
        if (tuesday.time.before(today.time)) {
            tuesday.add(Calendar.WEEK_OF_YEAR, 1)
        }
        Log.e("tuesday", tuesday.time.toString())

        return tuesday.timeInMillis - System.currentTimeMillis() - earlyInMillis
    }

    fun getWednesdayDateTime(mealTime: String, earlyInMillis: Long): Long {
        val today = Calendar.getInstance()
        val wednesday = Calendar.getInstance()
        wednesday.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY)

        val time = getTimeHrMin(mealTime)
        val hour = time[0]
        val minute = time[1]
        wednesday.set(Calendar.HOUR_OF_DAY, hour.toInt())
        wednesday.set(Calendar.MINUTE, minute.toInt())
        if (wednesday.time.before(today.time)) {
            wednesday.add(Calendar.WEEK_OF_YEAR, 1)
        }
        Log.e("Wednesday", wednesday.time.toString())
        return wednesday.timeInMillis - System.currentTimeMillis() - earlyInMillis
    }

    fun getThursdayDateTime(mealTime: String, earlyInMillis: Long): Long {
        val today = Calendar.getInstance()
        val thursday = Calendar.getInstance()
        thursday.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY)
        val time = getTimeHrMin(mealTime)
        val hour = time[0]
        val minute = time[1]
        thursday.set(Calendar.HOUR_OF_DAY, hour.toInt())
        thursday.set(Calendar.MINUTE, minute.toInt())
        if (thursday.time.before(today.time)) {
            thursday.add(Calendar.WEEK_OF_YEAR, 1)
        }
        Log.e("Thursday", thursday.time.toString())
        return thursday.timeInMillis - System.currentTimeMillis() - earlyInMillis
    }

    fun getFridayDateTime(mealTime: String, earlyInMillis: Long): Long {
        val today = Calendar.getInstance()
        val friday = Calendar.getInstance()
        friday.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
        val time = getTimeHrMin(mealTime)
        val hour = time[0]
        val minute = time[1]
        friday.set(Calendar.HOUR_OF_DAY, hour.toInt())
        friday.set(Calendar.MINUTE, minute.toInt())
        if (friday.time.before(today.time)) {
            friday.add(Calendar.WEEK_OF_YEAR, 1)
        }
        Log.e("Friday", friday.time.toString())
        return friday.timeInMillis - System.currentTimeMillis() - earlyInMillis
    }

    fun getSaturdayDateTime(mealTime: String, earlyInMillis: Long): Long {
        val today = Calendar.getInstance()
        val saturday = Calendar.getInstance()
        saturday.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
        val time = getTimeHrMin(mealTime)
        val hour = time[0]
        val minute = time[1]
        saturday.set(Calendar.HOUR_OF_DAY, hour.toInt())
        saturday.set(Calendar.MINUTE, minute.toInt())
        if (saturday.time.before(today.time)) {
            saturday.add(Calendar.WEEK_OF_YEAR, 1)
        }
        Log.e("Saturday", saturday.time.toString())
        return saturday.timeInMillis - System.currentTimeMillis() - earlyInMillis
    }

    fun getSundayDateTime(mealTime: String, earlyInMillis: Long): Long {
        val today = Calendar.getInstance()
        val sunday = Calendar.getInstance()
        sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val time = getTimeHrMin(mealTime)
        val hour = time[0]
        val minute = time[1]
        sunday.set(Calendar.HOUR_OF_DAY, hour.toInt())
        sunday.set(Calendar.MINUTE, minute.toInt())
        if (sunday.time.before(today.time)) {
            sunday.add(Calendar.WEEK_OF_YEAR, 1)
        }
        Log.e("Sunday", sunday.time.toString())
        return sunday.timeInMillis - System.currentTimeMillis() - earlyInMillis
    }

    private fun getTimeHrMin(time: String): List<String> {
        return time.split(":")
    }
}