package com.example.m15_room.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.example.m15_room.ui.main.database.Words
import com.example.m15_room.ui.main.database.WordDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {


    private val _state = MutableStateFlow<State>(State.Start)
    val state = _state.asStateFlow()

    var insertWord: String = ""


    init {
        State.Start
    }

    fun getAllWords(): StateFlow<List<Words>> {
        _state.value = State.Start
        return this.wordDao.getAll()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }


    /* fun getAllWords(): Flow<List<Words>> {
         _state.value = State.Start
         return wordDao.getAll().stateIn(
             scope = viewModelScope,
             started = SharingStarted.WhileSubscribed(5000),
             initialValue = emptyList()
         )

     }*/


    fun getWordMatches():Flow<List<Words>>? {
        var a: Flow<List<Words>>? = null
        if (insertWord != "") {
            a = wordDao.getAllCondition(insertWord)
            _state.value = State.Matches
        } else {
            _state.value = State.Start
        }
        return a
    }


    fun onAddBtn() {
        viewModelScope.launch {
            if (insertWord != "") {
                wordDao.insert(
                    Words(word = insertWord, count = 5)
                )
                _state.value = State.Start
            }


        }
    }

    fun onDeleteButton() {
        viewModelScope.launch {
            getAllWords().lastOrNull().let {
                wordDao.delete(it)
            } }
            /* getAllWords().observe(this@MainViewModel){
                 it.lastOrNull().let {
                     wordDao.delete(it)
                 }
             }*/
        }

    }


}

