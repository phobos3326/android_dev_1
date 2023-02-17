package com.example.m17_recyclerview.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m17_recyclerview.domain.GetPhotoUseCase
import com.example.m17_recyclerview.entity.ModelPhotos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val data: GetPhotoUseCase) : ViewModel() {
    constructor(): this(GetPhotoUseCase())

    private val _marsPhoto = MutableStateFlow<List<ModelPhotos.Photo>>(emptyList())
    val marsPhoto = _marsPhoto.asStateFlow()

    init {
        viewModelScope.launch {
            loadPhotos()
        }
    }


    private fun loadPhotos() {

        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
              data.execute().photos
                //Log.d("TAG", "$data.execute().photos")
            }.fold(
                onSuccess = {_marsPhoto.value = it },
                onFailure = {Log.d("MainViewModel", it.message?:"not Load")}
            )

        }


    }

}