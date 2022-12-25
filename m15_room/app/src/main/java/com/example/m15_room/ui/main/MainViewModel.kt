package com.example.m15_room.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.m15_room.ui.main.database.WordDao
import com.example.m15_room.ui.main.database.Words
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.regex.Pattern


class MainViewModel(private val wordDao: WordDao, application: Application) :
    AndroidViewModel(application) {
    var insertWord: String = ""


    var allWords = this.wordDao.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    var matchList = this.wordDao.getAllCondition(insertWord).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    /*     .stateIn(
         scope = viewModelScope,
         started = SharingStarted.WhileSubscribed(),
         initialValue = emptyList()
     )*/

    //var matchList = null

    private var _state = MutableStateFlow<State>(State.Start)
    var state = _state.asStateFlow()


    init {
        _state.value = State.Start
    }


    fun getWordMatches(): Flow<List<Words>>? {
        var list: StateFlow<List<Words>>? = matchList
        if (insertWord != "") {
            matchList = wordDao.getAllCondition(insertWord).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = emptyList()
            )
            list = matchList
            // _state.value = State.Matches(matchList)
            Log.d("state", "match1")
        } else if (insertWord == "") {
            // _state.value = State.Start(allWords)
            list = allWords
            Log.d("state", "start")
        }
        return list
    }


    suspend fun onAddBtn() {
        onUpdate()

        /*  var a =0
         viewModelScope.launch {

             a = matchList.count()

             Log.d("WWW", a.toString())
             if (insertWord != "" && a == 0) {


             }
             if (insertWord != "" && matchList.count() == 1) {
                 wordDao.insert(Words(word = insertWord, count = 0))
             }
         }*/


    }

    private suspend fun onUpdate() {
        viewModelScope.launch {

            matchList?.value?.lastOrNull().let {
                val aa = it?.copy(
                    word = insertWord,
                    count = it.count + 1
                )
                wordDao.update(aa)
            }
        }

        /* matchList.lastOrNull().let {
             it?.copy(
                 word = insertWord,
                 count = it.count + 1
             )
             wordDao.update(it)
         }*/
        // _state.value = State.Start(allWords)
        // }
        //  _state.value = State.Matches(matchList)
        //Log.d("state", "match4")
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

