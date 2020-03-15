package com.zotiko.spacelaunchnow.ui.extension

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

const val DATE_FORMAT_EE_DD_MM_YYYY_HH_MM_A = "EEEE d MMM yyyy, hh:mm a"

fun Date.format(dateFormat: String = "dd MMM, HH:mm"): String {
    val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.US)
    // TODO need to specify timezone? - need discussion on this
    return simpleDateFormat.format(this)
}

fun Date.getCalendarDate(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}
