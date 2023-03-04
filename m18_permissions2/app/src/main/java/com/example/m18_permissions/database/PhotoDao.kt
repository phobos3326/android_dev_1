package com.example.m18_permissions.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo")
    fun getAll(): Flow<List<Photo>>

    @Insert(entity = Photo::class, OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)

    @Delete
    suspend fun delete(photo: Photo)

    @Update
    suspend fun update(photo: Photo?)
}
