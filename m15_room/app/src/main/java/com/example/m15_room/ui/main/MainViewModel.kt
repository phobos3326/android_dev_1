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

    var insertWord: String = ""
    private var allWords: List<String> = mutableListOf()

    private var _state = MutableStateFlow<State>(State.Start)
    var state = _state.asStateFlow()


    init {
        _state.value = State.Start
        viewModelScope.launch {
            wordDao.getAll().onEach { words ->
                allWords = words.map { it.word }
                _state.value = State.Content(words.take(5))
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

        /* viewModelScope.launch {

             allWords.value?.lastOrNull()?.let {
                 wordDao.delete(it)

             }
         }
         _state.value=State.Start(allWords)*/
    }


    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" +
                //"(?=.*[@#$%^&+=!])" +  // at least 1 special character
                "(?=.*[^0-9])" +  // at least 1 special character

                "(?=\\S+$)" +  // no white spaces
                ".{4,}" +  // at least 4 characters
                "$"
    )


    fun validatePassword(): Boolean {
        val passwordInput = insertWord
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty()) {
            Log.d("TAG", "Field can not be empty")
            _state.value = State.ErrorInput
            //  password!!.error = "Field can not be empty"
            return false
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            //   password!!.error = "Password is too weak"
            _state.value = State.WhiteSpaces
            Log.d("TAG", "Password is too weak")
            return false
        } else {
            // password!!.error = null
            Log.d("TAG", "null")

            return true
        }
    }


}

