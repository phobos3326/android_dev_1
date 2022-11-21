package com.example.m15_room.ui.main.database

sealed class State {
    object Start : State()
    object Clear : State()
    object Matches : State()
}
