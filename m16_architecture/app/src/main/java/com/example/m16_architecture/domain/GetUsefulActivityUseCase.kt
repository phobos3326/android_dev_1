package com.example.m16_architecture.domain


import com.example.m16_architecture.data.UsefulActivityDto
import com.example.m16_architecture.data.UsefulActivityRepository
import com.example.m16_architecture.entity.UsefulActivity
import retrofit2.Response


class GetUsefulActivityUseCase(private val repository: UsefulActivityRepository) {

    suspend fun execute(): UsefulActivity {
        return repository.getUsefulActivity()
    }
}