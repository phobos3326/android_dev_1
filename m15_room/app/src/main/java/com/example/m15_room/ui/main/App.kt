package com.example.m15_room.ui.main

import android.app.Application
import androidx.room.Room
import com.example.m15_room.ui.main.database.AppDataBase

class App : Application() {

    lateinit var db: AppDataBase
    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "db"
        ).build()
    }
}