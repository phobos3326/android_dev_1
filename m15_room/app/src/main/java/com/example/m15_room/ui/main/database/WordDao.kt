package com.example.m15_room.ui.main.database


import android.view.View.OnClickListener
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface WordDao {
    @Query("SELECT * FROM words ORDER BY count LIMIT 5")
    fun getAll(): Flow<List<Words>>

    @Query("SELECT * FROM words WHERE word LIKE :insertWord")
    fun getAllCondition(insertWord: String): Flow<List<Words>>

    @Insert(entity = Words::class)
    suspend fun insert(words: Words)

    @Delete
    suspend fun delete(words: Words)

    @Update
    suspend fun update(words: Words?)


}