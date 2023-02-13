package com.example.m17_recyclerview.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m17_recyclerview.domain.GetPhotoUseCase
import com.example.m17_recyclerview.entity.ModelPhotos
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    init {
        viewModelScope.launch {
            loadPhotos()
        }
    }


     suspend fun loadPhotos(): List<ModelPhotos.Photo> {

        val data = GetPhotoUseCase().execute()
        val photo = data.photos
        Log.d("TAG", "$photo")

        return photo
    }

}