package com.example.m18_permissions.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "photo")
data class Photo(
    @PrimaryKey
    @ColumnInfo(name="photoURI")
    var photo: String
)
