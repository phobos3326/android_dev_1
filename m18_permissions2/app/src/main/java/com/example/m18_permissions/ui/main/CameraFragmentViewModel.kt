package com.example.m18_permissions.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.m18_permissions.database.Photo
import com.example.m18_permissions.database.PhotoDao
import com.example.m18_permissions.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraFragmentViewModel @Inject constructor(private val repository: Repository):ViewModel() {

    suspend fun insert(photo: Photo) {
        repository.insertPhoto(photo)
    }

}

