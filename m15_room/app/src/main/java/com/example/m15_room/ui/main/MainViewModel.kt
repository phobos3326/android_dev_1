package com.example.m15_room.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.m15_room.ui.main.database.WordDao
import com.example.m15_room.ui.main.database.Words
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern


class MainViewModel(private val wordDao: WordDao, application: Application) :
    AndroidViewModel(application) {
    var insertWord: String = ""

    val allWords = this.wordDao.getAll().asLiveData()
    var matchList = this.wordDao.getAllCondition(insertWord).asLiveData()
    //var ldList=matchList?.asLiveData()

    private var _state = MutableStateFlow<State>(State.Start(allWords))
    var state = _state.asStateFlow()


    init {
        _state.value = State.Start(allWords)
    }




   suspend fun getWordMatches() {

            if (insertWord != "") {

                matchList = wordDao.getAllCondition(insertWord).asLiveData()
                Log.d("state1", "$matchList")

                _state.value = State.Matches(matchList)

               // return matchList
            } else {
                _state.value = State.Start(allWords)

                // return null
            }
          }


    fun onAddBtn() {

        viewModelScope.launch {
            if (insertWord != "" && matchList?.value?.size != 0

            ) {
                onUpdate()
            }
            if (insertWord != "" &&  matchList?.value?.size == 0
            ) {
                wordDao.insert(Words(word = insertWord, count = 0))
                _state.value = State.Start(allWords)
            }
        }
    }

    private suspend fun onUpdate() {
        viewModelScope.launch {
            var aa: Words?
            matchList?.value?.lastOrNull().let {
                aa = it?.copy(
                    word = insertWord,
                    count = it.count + 1
                )
            }
            wordDao.update(aa)
            _state.value = State.Start(allWords)
        }
    }


    fun onDeleteButton() {

        /*viewModelScope.launch {

            allWords.value?.lastOrNull()?.let {
                wordDao.delete(it)
                //_state.value=State.Start
            }
        }*/

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

