package com.example.m14_retrofit.ui.main

sealed class State{
    object ColdStart:State()
    object Completed:State()
    object Wait:State()
    object Error:State()
}
