package com.example.m12_mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val _state = MutableStateFlow<State>(State.Completed)
    val state = _state.asStateFlow()

    init {
        _state.value = State.Blank
    }

    var findString = false
    var stateFind = false

    var requestStr = ""
    var requestResult = ""

    fun strLength(str: String) {
        requestStr = str
        if (str.length <= 3) {
            _state.value = State.Blank
        }
        if (str.length > 3) {
            _state.value = State.Ready
            findString = true
        }
        if (findString && stateFind) {
            _state.value = State.Find
        }
    }


    suspend fun onClick() {
        _state.value = State.Find
        stateFind = true
        delay(3000)
        _state.value = State.Completed
        requestResult()
        stateFind = false
    }

    fun requestResult(): String {
        requestResult = buildString {
            append(getApplication<Application>().resources.getString(R.string.on_request))
            append(requestStr)
            append(getApplication<Application>().resources.getString(R.string.nothing_found))
        }
        return requestResult
    }

}