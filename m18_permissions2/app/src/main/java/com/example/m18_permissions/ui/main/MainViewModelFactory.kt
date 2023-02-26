package com.example.m18_permissions.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.m18_permissions.database.PhotoDao

class MainViewModelFactory(var app: PhotoDao):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){

            return MainViewModel(app) as T
        }
        throw  IllegalAccessException("xz")
    }
}