package com.arashaghelifar.ui.utils

import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Locale

fun Long.mapTimestampToDateText(): String {

    val dateTime = Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDateTime()
    val today = LocalDate.now()
    val tomorrow = today.plusDays(1)
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    return when {
        dateTime.toLocalDate().isEqual(today) -> {
            "today, ${dateTime.format(timeFormatter)}"
        }
        dateTime.toLocalDate().isEqual(tomorrow) -> {
            "tomorrow, ${dateTime.format(timeFormatter)}"
        }
        else -> {
            val dateFormatter = DateTimeFormatter.ofPattern("dd/MM")
            "${dateTime.format(dateFormatter)} ${dateTime.format(timeFormatter)}"
        }
    }
}

fun String.convertToTimestamp(): Long {
    // Define the date format for parsing (e.g., "5 March 2024 14:30")
    val dateFormat = SimpleDateFormat("d MMMM yyyy HH:mm", Locale.getDefault())

    return try {
        // Parse the date string to a Date object
        val date = dateFormat.parse(this)

        // Return the timestamp in milliseconds (epoch time)
        date?.time ?: 0L // Return 0 if the date is null (parsing fails)
    } catch (e: Exception) {
        e.printStackTrace()
        0L // Return 0 if parsing fails
    }
}
