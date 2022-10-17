package com.example.m12_mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _state= MutableStateFlow<State>(State.Completed)
    val state=_state.asStateFlow()



    fun String.strLength()=length>3

    fun onClick(){
        viewModelScope.launch {
            repository.getData()
        }

    }


}