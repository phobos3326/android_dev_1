package com.example.m15_room.ui.main

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m15_room.ui.main.DataBase.NewWord
import com.example.m15_room.ui.main.DataBase.Word
import com.example.m15_room.ui.main.DataBase.WordDao
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch
import kotlin.reflect.KProperty1

class MainViewModel(private val wordDao: WordDao) : ViewModel() {


    var words = this.wordDao.getAll().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )




      fun onAdd(){
          val size =words.value.size
         viewModelScope.launch {

             wordDao.insert(
                 NewWord(
                     word = "word $size",
                     count = size
                 )
             )
         }
     }


    fun onDelete(){
        viewModelScope.launch {
           words.value.lastOrNull()?.let {
               wordDao.delete(it)
           }
        }
    }


}

