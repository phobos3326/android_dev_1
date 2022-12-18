package com.example.m15_room.ui.main

import androidx.lifecycle.LiveData
import com.example.m15_room.ui.main.database.Words
import kotlinx.coroutines.flow.Flow

sealed class State {
    object Start : State()
    object Clear : State()
    object Matches : State()
    object ErrorInput : State()
    object WhiteSpaces : State()
    object Validate : State()

}