package com.arashaghelifar.note_details.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

fun scheduleReminder(context: Context, noteId: Int, reminderTime: Long) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra("noteId", noteId)
    }


    val pendingIntent = PendingIntent.getBroadcast(
        context,
        noteId,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    alarmManager.setExact(
        AlarmManager.RTC_WAKEUP,
        reminderTime,
        pendingIntent
    )
}