package com.example.m19_location.domain

import com.example.m19_location.data.ModelLandmarkDto
import com.example.m19_location.data.ModelLandmarkRepository

class UseCase(private val repository: ModelLandmarkRepository) {
    suspend fun getLandmarkUseCase():ModelLandmarkDto{
        return repository.getLandmark()
    }
}