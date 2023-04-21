package com.ithirteeng.features.chat.presentation.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

object DateHelper {
    private val zoneOffset: Long = getZoneOffset()

    private fun getZoneOffset(): Long {
        val mCalendar: Calendar = GregorianCalendar()
        val mTimeZone = mCalendar.timeZone
        val mGMTOffset = mTimeZone.rawOffset.toLong()
        return TimeUnit.HOURS.convert(mGMTOffset, TimeUnit.MILLISECONDS)
    }

    fun getDate(dateString: String): String {
        val dateTime = LocalDateTime.parse(dateString).plusHours(zoneOffset)
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMMM"))
    }

    fun checkIdDateIsToday(dateString: String): Boolean {
        val dateTime = LocalDateTime.parse(dateString).plusHours(zoneOffset)

        return dateTime.toLocalDate() == LocalDateTime.now().toLocalDate()
    }

    fun getTime(dateString: String): String {
        val dateTime = LocalDateTime.parse(dateString).plusHours(zoneOffset)
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
    }
}