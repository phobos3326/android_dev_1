package com.example.m18_permissions.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m18_permissions.State
import com.example.m18_permissions.database.Photo
import com.example.m18_permissions.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {



    val dataBaseScope = CoroutineScope(Dispatchers.IO)

    val listPhoto: List<Photo>? = null

    var allPhoto: List<String> = mutableListOf()

    private var _state = MutableStateFlow<State>(State(photo = listPhoto))
    var state = _state.asStateFlow()


    init {
        _state.value = State(photo = listPhoto)
        viewModelScope.launch {
            repository.getAllPhoto().onEach { photo ->
                allPhoto = photo.map {
                    it.photo
                }
                _state.value = State(photo)
            }.collect()
        }


    }

    suspend fun takeOne(): String {
        return repository.getOnePhoto()
    }

    suspend fun insert(photo: Photo) {
        repository.insertPhoto(photo)
    }


}
