package com.example.fitnessapp.helper

import android.util.Log
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Locale

object TimeHelper {
    fun getShortDaysOfWeek(locale: Locale = Locale.getDefault()): List<String> {
        return DayOfWeek.entries.map { it.getDisplayName(TextStyle.SHORT, locale) }
    }

    fun getCurrentDayTimeInMillis(): Long = System.currentTimeMillis()

    fun isToday(millis: Long): Boolean {
        val today = LocalDate.now()
        val dateFromMillis = Instant.ofEpochMilli(millis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        return dateFromMillis == today
    }

    fun getDateFromMillis(millis: Long, dateFormat: String): String {
        val formatter = SimpleDateFormat(dateFormat)

        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return formatter.format(calendar.getTime())
    }

    fun getTomorrowDayInMillis(millis: Long): Long {
        val cal = Calendar.getInstance()
        cal.timeInMillis = millis
        cal.add(Calendar.DAY_OF_MONTH, 1)
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.set(Calendar.MILLISECOND, 999)

        return cal.timeInMillis
    }

    fun getYesterdayInMillis(millis: Long): Long {
        val cal = Calendar.getInstance()
        cal.timeInMillis = millis
        cal.add(Calendar.DAY_OF_MONTH, -1)
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.set(Calendar.MILLISECOND, 999)

        return cal.timeInMillis
    }

    object DateFormats {
        const val DD_MM_YYYY = "dd/MM/yyyy"
        val MMM_D_YYYY = "MMM d, yyyy"
    }
}