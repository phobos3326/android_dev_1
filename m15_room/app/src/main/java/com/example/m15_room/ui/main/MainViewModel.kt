package com.example.m15_room.ui.main

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.m15_room.ui.main.DataBase.WordDao

class MainViewModel(application: App, private val wordDao: WordDao) :
    AndroidViewModel(application) {
    // TODO: Implement the ViewModel

        val words=this.wordDao.getAll()

}