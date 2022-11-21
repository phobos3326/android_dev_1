package com.example.m15_room.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m15_room.ui.main.database.State

import com.example.m15_room.ui.main.database.Words
import com.example.m15_room.ui.main.database.WordDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {


    private val _state = MutableStateFlow<State>(State.Start)
    val state = _state.asStateFlow()

    var insertWord: String = ""


    init {
        State.Start
    }


    fun getAllWords(): LiveData<List<Words>> {
      _state.value = State.Start
        return wordDao.getAll()
    }
/*     .stateIn(
         scope = viewModelScope,
         started = SharingStarted.WhileSubscribed(5000),
         initialValue = emptyList()
     )*/


    fun getWordMatches(): LiveData<List<Words>>? {
        var a: LiveData<List<Words>>? = null
        if (insertWord != "") {
            a = wordDao.getAllCondition(insertWord)
            _state.value = State.Matches
        }else{
            _state.value=State.Clear
        }
        return a
    }


    fun onAddBtn() {
        viewModelScope.launch {
            if (insertWord != "") {
                wordDao.insert(
                    Words(word = insertWord, count = 5)
                )
            }


        }
    }

    fun onDeleteButton() {
        viewModelScope.launch {
            getAllWords().value?.lastOrNull()?.let {
                wordDao.delete(it)
            }
        }

    }


}

