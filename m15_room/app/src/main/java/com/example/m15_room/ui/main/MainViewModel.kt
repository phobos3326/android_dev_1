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

    val allWords = this.wordDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    fun getWordMatches(): StateFlow<List<Words>>? {
        return if (insertWord!=""){
            _state.value=State.Matches
            this.wordDao.getAllCondition(insertWord)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = emptyList()
                )

        }else{
            _state.value=State.Start
            wordDao.getAll() .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

        }

    }





    fun onAddBtn() {
        _state.value = State.Start

        viewModelScope.launch {

            wordDao.insert(
                Words(word = insertWord, count = 5)
            )

        }
    }

    fun onDeleteButton() {
        _state.value = State.Start
        viewModelScope.launch {

            allWords.value?.lastOrNull()?.let {
                wordDao.delete(it)
            }
        }

    }


}

