package com.example.m15_room.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.m15_room.ui.main.database.WordDao
import com.example.m15_room.ui.main.database.Words
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern


class MainViewModel(private val wordDao: WordDao, application: Application) :
    AndroidViewModel(application) {
    var insertWord: String = ""

    val allWords = this.wordDao.getAll()
    var matchList: List<Words>? = null


    private var _state = MutableStateFlow<State>(State.Start)
    var state = _state.asStateFlow()


    init {
        _state.value = State.Start
    }


    //  var list = emptyList<Words>()

   suspend fun getWordMatches(): List<Words>? {
       // viewModelScope.launch {
            if (insertWord != "") {


                matchList = wordDao.getAllCondition(insertWord)
                Log.d("state1", "$matchList")
                // _state.value = State.Matches
                _state.value = State.Matches
                //return matchList
                /*matchList?.collect {
                    list = it
                }*/
                return matchList

            } else {
                _state.value = State.Start
                // return wordDao.getAll().asLiveData()
                 return null
            }
      //  }


    }

    /*fun changeState() {
        viewModelScope.launch {
            _state.value = State.Matches
        }

    }*/

    fun onAddBtn() {
        //_state.value = State.Matches
        viewModelScope.launch {
            if (insertWord != "" && matchList?.size != 0
            /* (
             scope = viewModelScope,
             started = SharingStarted.WhileSubscribed(5000),
             initialValue = emptyList()
         )?*/
            ) {

                onUpdate()

            }
            if (insertWord != "" && matchList?.size == 0 /*  .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = emptyList()
                )?*/
            ) {
                wordDao.insert(Words(word = insertWord, count = 0))
                _state.value = State.Start
            }
        }
    }

    private suspend fun onUpdate() {
        viewModelScope.launch {
            var aa: Words?
            matchList?.lastOrNull().let {
                aa = it?.copy(
                    word = insertWord,
                    count = it.count + 1
                )
            }
            wordDao.update(aa)
            _state.value = State.Start

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

