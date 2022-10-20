package com.example.m12_mvvm

sealed class State{
    object Completed:State()
    object Find:State()

    object Ready:State()
    object Blank:State()
}
