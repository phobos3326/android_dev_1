package com.example.m15_room.ui.main

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m15_room.ui.main.DataBase.Word
import com.example.m15_room.ui.main.DataBase.WordDao

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {


    val words = this.wordDao.getAll()

    /*private val wordRepository = WordRepository.get()
    val words=wordRepository.getWords()*/


     fun onAdd(){
         viewModelScope.launch {
             wordDao.insert(
                 Word(
                     id=1,
                     word = "word",
                     count = 3
                 )
             )
         }
     }

}