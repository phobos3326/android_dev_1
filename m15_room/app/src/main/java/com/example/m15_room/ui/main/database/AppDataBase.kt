package com.example.m15_room.ui.main.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Words::class], version = 1/*,
autoMigrations = [
    AutoMigration(from = 1, to = 2)
]*/)
abstract class AppDataBase:RoomDatabase() {
    abstract fun wordDao():WordDao
}