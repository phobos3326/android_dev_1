package com.example.m15_room.ui.main.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.nio.file.attribute.UserPrincipal

@Dao
interface WordDao {
    @Query("SELECT * FROM word")
    fun getAll(): Flow<List<Word>>

    @Insert
    suspend fun insert(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Update
    suspend fun update(word: Word)
}