package com.example.m15_room.ui.main

import androidx.lifecycle.LiveData
import com.example.m15_room.ui.main.database.Words
import kotlinx.coroutines.flow.Flow

sealed class State {
    data class Start(var allWords: LiveData<List<Words>>) : State()
    object Clear : State()
    data class Matches(var listMatches:LiveData<List<Words>>?) : State()
    object ErrorInput : State()
    object WhiteSpaces : State()
    object Validate : State()

}