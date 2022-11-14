package com.example.m15_room.ui.main

import android.app.Application
import androidx.room.Room
import com.example.m15_room.ui.main.DataBase.AppDataBase


class App : Application() {

    //lateinit var db: AppDataBase

    /*override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "db"
        ).build()
    }*/


    private var instance: App? = null

    lateinit var db: AppDataBase

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(this, AppDataBase::class.java, "database")
            .build()
    }

    fun getInstance(): App? {
        return instance
    }

    fun getDatabase(): AppDataBase? {
        return db
    }




}