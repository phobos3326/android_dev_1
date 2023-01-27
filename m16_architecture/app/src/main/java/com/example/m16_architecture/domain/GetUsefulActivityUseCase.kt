package com.example.m16_architecture.domain


import android.app.Application
import com.example.m16_architecture.data.UsefulActivityDto
import com.example.m16_architecture.data.UsefulActivityRepository
import com.example.m16_architecture.entity.UsefulActivity
import dagger.hilt.android.internal.modules.ApplicationContextModule
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUsefulActivityUseCase @Inject constructor(
    private val retrofitInstance: UsefulActivityRepository ) {

   suspend fun execute(): Response<UsefulActivityDto> {
        return retrofitInstance.retrofitInstanse()
    }
}