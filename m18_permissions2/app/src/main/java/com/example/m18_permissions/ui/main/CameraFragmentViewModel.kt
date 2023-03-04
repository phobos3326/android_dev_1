package com.example.m18_permissions.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.m18_permissions.database.Photo
import com.example.m18_permissions.database.PhotoDao

class CameraFragmentViewModel(private val photoDao: PhotoDao):ViewModel() {


    suspend fun insert(photo: Photo) {
        photoDao.insert(photo)
    }

}


class CameraFragmentViewModelFactory(var app: PhotoDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CameraFragmentViewModel::class.java)){

            return CameraFragmentViewModel(app) as T
        }
        throw  IllegalAccessException("xz")
    }
}