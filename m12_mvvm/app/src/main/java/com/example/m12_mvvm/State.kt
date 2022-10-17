package com.example.m12_mvvm

sealed class State{
    object Find:State()
    object Completed:State()
}
