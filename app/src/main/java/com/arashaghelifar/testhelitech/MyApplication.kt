package com.arashaghelifar.testhelitech

import android.app.Application
import com.arashaghelifar.note_details.reminder.createNotificationChannel
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        createNotificationChannel(this)
    }


}