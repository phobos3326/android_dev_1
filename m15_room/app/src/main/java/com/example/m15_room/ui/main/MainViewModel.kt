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

    private var matchList: StateFlow<List<Words>>? = null

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
        if (insertWord != "") {
            _state.value = State.Matches
            matchList = this.wordDao.getAllCondition(insertWord)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = emptyList()
                )

            return matchList

        } else {
            _state.value = State.Start
            return wordDao.getAll().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

        }
    }


    fun onAddBtn() {
        _state.value = State.Start
        viewModelScope.launch {
            if (insertWord != "" && matchList?.value?.size == 1) {
                onUpdate()
            }
            if (insertWord != "" && matchList?.value?.size == 0) {
            wordDao.insert(
                Words(word = insertWord, count = 0)
            )
        }

        }
    }

    suspend fun onUpdate() {
        matchList?.value?.lastOrNull().let {
            val aa = it?.copy(
                word = insertWord,
                count = it.count + 1
            )
            if (aa != null) {
                wordDao.update(aa)
            }
        }
    }

    fun onDeleteButton() {
        _state.value = State.Start
        viewModelScope.launch {
            allWords.value.lastOrNull()?.let {
                wordDao.delete(it)
            }
        }
    }
}

