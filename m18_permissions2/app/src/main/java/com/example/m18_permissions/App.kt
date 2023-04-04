package com.example.m18_permissions

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.m18_permissions.database.AppDataBase
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
            createNotificationChannel()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(){
        val name =  "Test notification channel"
        val descriptionText ="Simple description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT


        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name,importance).apply {
            description =descriptionText
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    companion object{
         const val NOTIFICATION_CHANNEL_ID ="test_channel_ID"
    }

}