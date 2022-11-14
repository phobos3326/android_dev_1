package com.example.m15_room.ui.main.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class NewWord(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:Int?=null,
    @ColumnInfo(name = "word")
    val word:String?=null,
    @ColumnInfo(name = "count")
    val count:Int
)
