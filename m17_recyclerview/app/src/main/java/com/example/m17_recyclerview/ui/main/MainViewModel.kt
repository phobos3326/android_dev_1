package com.example.m17_recyclerview.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m17_recyclerview.domain.GetPhotoUseCase
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    init {
        loadPhotos()
    }



    private fun loadPhotos() {
        viewModelScope.launch {
            val data =GetPhotoUseCase().execute()
            val photo = data.photos
            Log.d("TAG", "$photo")
        }
    }

}