package com.example.m15_room.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m15_room.ui.main.database.Words
import com.example.m15_room.ui.main.database.WordDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {

    var insertWord: String = ""


    val allWords = this.wordDao.getAll()
    /* .stateIn(
         scope = viewModelScope,
         started = SharingStarted.WhileSubscribed(5000),
         initialValue = emptyList()
     )*/


    fun getGetWordMatches(): Flow<List<Words>> {
        return wordDao.getAllCondition(insertWord)
    }


    fun onAddBtn() {
        viewModelScope.launch {
            wordDao.insert(
                Words(word = insertWord, count = 5)
            )
            val a = getGetWordMatches().count()
            Log.d("TAG", a.toString())
        }
    }
}