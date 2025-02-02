package com.example.m15_room.ui.main.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM words ORDER BY count LIMIT 5")
    fun getAll(): Flow<List<Words>>

    @Query("SELECT * FROM words WHERE word LIKE :insertWord")
    fun getAllCondition(insertWord: String): Words

    @Insert(entity = Words::class)
    suspend fun insert(words: Words)

    @Delete
    suspend fun delete(words: Words)

    @Update
    suspend fun update(words: Words?)


}