package com.example.m15_room.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.m15_room.ui.main.database.Words
import com.example.m15_room.ui.main.database.WordDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {

    private var _state = MutableStateFlow<State>(State.Start)
    var state = _state.asStateFlow()

    var insertWord: String = ""


    /* private val _state = MutableLiveData<State>()
     val state : LiveData<State> get() = _state

     init {
         _state.value = State.Matches
     }*/

    private var matchList: StateFlow<List<Words>>? = null

    /*  init {
         _state.value= State.Start
      }*/

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
            _state.value = State.Matches
            //_state.value = State.Start
            return wordDao.getAll().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

        }
    }

    fun changeState() {
        viewModelScope.launch {
            _state.value = State.Matches
        }

    }

    fun onAddBtn() {

        viewModelScope.launch {

            if (insertWord != "" && matchList?.value?.size == 1) {
                _state.value = State.Matches
                onUpdate()
            }
            if (insertWord != "" && matchList?.value?.size == 0) {
                _state.value = State.Matches
                wordDao.insert(Words(word = insertWord, count = 0))

            }

        }
    }

    private suspend fun onUpdate() {
        viewModelScope.launch {
            matchList?.value?.lastOrNull().let {
                val aa = it?.copy(
                    word = insertWord,
                    count = it.count + 1
                )
                if (aa != null) {
                    wordDao.update(aa)
                    _state.value = State.Matches
                }
            }
        }

    }

    fun onDeleteButton() {

        viewModelScope.launch {
            _state.value = State.Clear
            allWords.value.lastOrNull()?.let {
                wordDao.delete(it)
            }
        }
    }
}

