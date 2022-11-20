package com.example.m15_room.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.m15_room.ui.main.database.Words
import com.example.m15_room.ui.main.database.WordDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {

   var insertWord: String = ""


    val allWords = this.wordDao.getAll()
/*     .stateIn(
         scope = viewModelScope,
         started = SharingStarted.WhileSubscribed(5000),
         initialValue = emptyList()
     )*/


     fun getGetWordMatches(): LiveData<List<Words>>? {
        val a= wordDao.getAllCondition(insertWord)
        return a
    }


    fun onAddBtn() {
        viewModelScope.launch {
          /*  val a = getGetWordMatches()
            Log.d("TAG", a.toString())*/

            wordDao.insert(
                Words(word = insertWord, count = 5)
            )

        }
    }

    fun onDeleteButton(){
        viewModelScope.launch {
            if (insertWord!=null){

            }
            allWords.value?.lastOrNull()?.let {
                wordDao.delete(it)
            }
        }

    }




}

