package com.example.m12_mvvm

import kotlinx.coroutines.delay

class Repository {

    suspend fun getData():String{
        delay(8000)
        return "completed"
    }
}