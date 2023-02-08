package com.example.m17_recyclerview.domain

import com.example.m17_recyclerview.data.ModelPhotoRepository
import com.example.m17_recyclerview.entity.ModelPhotos

class GetPhotoUseCase {

    suspend fun execute():ModelPhotos{
        return ModelPhotoRepository().getMarsPhotos()
    }
}