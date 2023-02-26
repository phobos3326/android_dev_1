package com.example.m18_permissions.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m18_permissions.State
import com.example.m18_permissions.database.Photo
import com.example.m18_permissions.database.PhotoDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(private val photoDao: PhotoDao) : ViewModel() {
    // TODO: Implement the ViewModel


    val dataBaseScope= CoroutineScope(Dispatchers.IO)

    val listPhoto: List<Photo>? = null

    private var allPhoto: List<String> = mutableListOf()

    private var _state = MutableStateFlow<State>(State(photo = listPhoto))
    var state = _state.asStateFlow()


    init {
        _state.value = State(photo = listPhoto)
        viewModelScope.launch {
           photoDao.getAll().onEach { photo ->
                allPhoto = photo.map {
                    it.photo
                }
                _state.value = State(photo.take(5))
            }.collect()
        }


    }

}