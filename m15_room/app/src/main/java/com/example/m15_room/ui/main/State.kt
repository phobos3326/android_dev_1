package com.example.m15_room.ui.main

import com.example.m15_room.ui.main.database.Words
import kotlinx.coroutines.flow.Flow


    data class State(val words: List<Words>?, val input: String, val flag:Boolean )


