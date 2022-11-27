package com.example.m15_room.ui.main

sealed class State {
    object Start : State()
    object Clear : State()
    object Matches : State()
    object ErrorInput : State()
    object WhiteSpaces : State()
    object Validate : State()

}