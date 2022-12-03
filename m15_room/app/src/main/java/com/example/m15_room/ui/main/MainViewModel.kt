package com.example.m15_room.ui.main

import android.app.Application
import android.content.Context
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.lifecycle.*

import com.example.m15_room.ui.main.database.Words
import com.example.m15_room.ui.main.database.WordDao
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class MainViewModel(private val wordDao: WordDao, application: Application) : AndroidViewModel(application) {




    private var _state = MutableStateFlow<State>(State.Start)
    var state = _state.asStateFlow()

    var insertWord: String = ""

    init {
        _state.value= State.Start
    }

    //private var email: TextInputLayout? = null
   // private var validateWord:String? = null


   /*  private val _state = MutableLiveData<State>()
     val state : LiveData<State> get() = _state

     init {
         _state.value = State.Matches
     }*/

    private var matchList: LiveData<List<Words>>? = null


    val allWords = this.wordDao.getAll().asLiveData()
    val allCondition =this.wordDao.getAllCondition(insertWord).asLiveData()

      /*  .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )*/


    fun getWordMatches(): LiveData<List<Words>>? {
        if (insertWord != "") {
            _state.value = State.Matches
            matchList = allCondition
             /*   .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = emptyList()
                )*/

            return matchList

        } else {
            _state.value = State.Matches
            //_state.value = State.Start
            return wordDao.getAll().asLiveData()
        /*.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )*/

        }
    }

    fun changeState() {
        viewModelScope.launch {
            _state.value = State.Matches
        }

    }

    fun onAddBtn() {
        viewModelScope.launch {
            if (insertWord != "" && matchList?.value?.size==1) {
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
            allWords.value?.lastOrNull()?.let {
                wordDao.delete(it)
            }
        }
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
            _state.value=State.ErrorInput
          //  password!!.error = "Field can not be empty"
            return false
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
         //   password!!.error = "Password is too weak"
            _state.value=State.WhiteSpaces
            Log.d("TAG", "Password is too weak")
            return false
        } else {
           // password!!.error = null
            Log.d("TAG", "null")

            return true
        }
    }

/*    fun confirmInput(v: View?) {
        if (!validatePassword()) {
            return
        }

        // if the email and password matches, a toast message
        // with email and password is displayed
        var input: String = "Email: " + email!!.editText!!.text.toString()
        input += "\n"
        input += "Password: " + password!!.editText!!.text.toString()

    }*/

}

