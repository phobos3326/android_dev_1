package com.example.m17_recyclerview.domain

import com.example.m17_recyclerview.data.ModelPhotoRepository
import com.example.m17_recyclerview.data.ModelPhotosDto
import com.example.m17_recyclerview.entity.ModelPhotos
import javax.inject.Inject


class GetPhotoUseCase @Inject constructor(private val repository: ModelPhotoRepository){

    suspend fun execute(): ModelPhotosDto{
        return repository.getMarsPhotos()
    }
}