package com.sun.livescore.ui.scores.parent

import com.sun.livescore.data.model.DateTime
import com.sun.livescore.ui.base.BaseViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ScoreViewModel : BaseViewModel() {

    fun addDateToTabLayout(): List<DateTime> {
        val dateTimes = ArrayList<DateTime>()
        val indexes = mutableListOf(-3, -2, -1, 0, 1, 2, 3)
        for (i in 0 until indexes.size) {
            dateTimes.add(getDateTimeForTabLayout(indexes[i]))
        }
        return dateTimes
    }

    private fun getDateTimeForTabLayout(index: Int): DateTime {
        val calendars = Calendar.getInstance()
        calendars.add(Calendar.DAY_OF_YEAR, index)
        val calendar = calendars.time
        val dateFormat = SimpleDateFormat(dateFormatSyntax, Locale.US)
        val dayFormat = SimpleDateFormat(dayFormatSyntax, Locale.US)
        val date = dateFormat.format(calendar)
        val day = dayFormat.format(calendar)
        return DateTime(day, date)
    }

    companion object {
        const val dateFormatSyntax = "dd"
        const val dayFormatSyntax = "EE"
    }
}
