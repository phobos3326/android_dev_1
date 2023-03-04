package com.example.m18_permissions

import android.app.Application
import androidx.room.Room
import com.example.m18_permissions.database.AppDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

   /* lateinit var db: AppDataBase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "db"
        ).build()
    }*/
}