package com.example.m12_mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Completed)
    val state = _state.asStateFlow()

    init {
        _state.value = State.Blank
    }

    var findString = false
    var stateFind = false

    fun strLength(str: String) {

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
        delay(10000)
        _state.value = State.Completed
        stateFind = false


    }


}