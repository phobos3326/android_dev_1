package com.example.m19_location.domain



import com.example.m19_location.data.ModelLandmarkRepository
import com.example.m19_location.data.ModelLandmarksDto
import javax.inject.Inject


class UseCase @Inject constructor(private val repository: ModelLandmarkRepository) {
    suspend fun getLandmarkUseCase(): ModelLandmarksDto{
        return repository.getLandmark()
    }
}