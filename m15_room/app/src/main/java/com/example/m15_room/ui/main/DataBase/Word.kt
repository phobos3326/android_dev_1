package com.example.m15_room.ui.main.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name = "word")
    val word:String,
    @ColumnInfo(name = "count")
    val count:Int
)
