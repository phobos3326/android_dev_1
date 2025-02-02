package com.example.m18_permissions.repository

import com.example.m18_permissions.database.Photo
import com.example.m18_permissions.database.PhotoDao
import javax.inject.Inject

class Repository @Inject constructor(private val photoDao: PhotoDao) {
    fun getAllPhoto() = photoDao.getAll()

    suspend fun insertPhoto(photo: Photo) = photoDao.insert(photo)

}