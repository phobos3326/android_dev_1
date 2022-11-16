package com.example.m15_room.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m15_room.ui.main.database.Words
import com.example.m15_room.ui.main.database.WordDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {

    var insertWord: String=""


    val allWords = this.wordDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    val wordMatches =this.wordDao.getAllCondition(insertWord)




    fun onAddBtn() {
        val count = allWords.value.size
        viewModelScope.launch {
            wordDao.insert(
                Words(
                    word = insertWord,
                    count = count
                )
            )
        }
    }
}