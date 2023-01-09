package com.example.m15_room.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.m15_room.ui.main.database.WordDao
import com.example.m15_room.ui.main.database.Words
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.regex.Pattern


class MainViewModel(private val wordDao: WordDao, application: Application) :
    AndroidViewModel(application) {

    val dataBaseScope = CoroutineScope(Dispatchers.IO)

    var input: String = ""

    val error = ""

    var insertWord: String = ""
    private var allWords: List<String> = mutableListOf()


    val listWords: List<Words>? = null

    private var _state = MutableStateFlow<State>(State(words = listWords, error, flag = true))
    var state = _state.asStateFlow()


    init {
        _state.value = State(words = listWords, error, flag = true)
        viewModelScope.launch {
            wordDao.getAll().onEach { words ->
                allWords = words.map {
                    it.word
                }
                _state.value = State(words.take(5), input, flag = true)
            }.collect()
        }
    }

    fun onAddBtn() {
        if (allWords.contains(insertWord)) {
            dataBaseScope.launch {
                val words = wordDao.getAllCondition(insertWord)
                val newCount = words.count + 1
                wordDao.update(words.copy(count = newCount))
            }
        } else {
            dataBaseScope.launch {
                wordDao.insert(Words(word = insertWord, count = 1))
            }
        }
    }


    fun onDeleteButton() {
        dataBaseScope.launch {
            allWords.lastOrNull()?.let {
                val word = wordDao.getAllCondition(it)
                wordDao.delete(word)
            }
        }
    }

    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
      "^" +                 //"(?=.*[@#$%^&+=!])" +  // at least 1 special character
                "(?=.*[^0-9])" +  // at least 1 special character

              //kjh  "(?=\\S+$)" +  // no white spaces
                ".{4,}" +  // at least 4 characters
                "$"
    )


    fun validatePassword() {
        viewModelScope.launch {
            var oldState = _state.value
            if (insertWord.isEmpty()) {
                var newState = oldState.copy(oldState.words, "", false)
                _state.value = newState
                Log.d("TAG", "Field can not be empty")

            } else if (!PASSWORD_PATTERN.matcher(insertWord).matches()) {
                var newState = oldState.copy(oldState.words, "Password is too weak", false)
                _state.value = newState
                Log.d("TAG", "Password is too weak")

            } else {
                var newState = oldState.copy(words = oldState.words, input = "", flag = true)
                _state.value = newState
                Log.d("TAG", "null")

            }
        }
    }
}