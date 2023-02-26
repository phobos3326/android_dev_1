package com.example.m18_permissions.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Photo::class], version = 1)
abstract class AppDataBase:RoomDatabase() {
    abstract fun photoDao():PhotoDao
}