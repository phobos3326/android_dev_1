package com.example.m17_recyclerview.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val mainViewModel: MainViewModel
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return mainViewModel as T
        }
        throw IllegalAccessException("xz")
    }
}