package com.example.m19_location.domain


import com.example.m19_location.data.ModelLandmarkDto
import com.example.m19_location.data.ModelLandmarkRepository
import com.example.m19_location.entity.ModelLandmark

class UseCase(private val repository: ModelLandmarkRepository) {
    suspend fun getLandmarkUseCase(): ModelLandmark.modelItem {
        return repository.getLandmark()
    }
}