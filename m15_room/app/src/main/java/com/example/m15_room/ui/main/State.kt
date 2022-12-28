package com.example.m15_room.ui.main

import com.example.m15_room.ui.main.database.Words
import kotlinx.coroutines.flow.Flow

sealed class State {
    //data class Start(var allWords: List<Words>) : State()
    object Start : State()
    object Clear : State()
    data class Content(
        val words: List<Words>,
        val input:String
    ) : State()

    object Matches : State()

    //data class Matches(var listMatches: List<Words>?) : State()
    object ErrorInput : State()
    object WhiteSpaces : State()
    object Validate : State()

}