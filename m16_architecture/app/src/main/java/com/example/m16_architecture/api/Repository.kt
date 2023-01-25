package com.example.m16_architecture.api

import com.example.m16_architecture.api.ActivityModel
import com.example.m16_architecture.api.AppModule
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val retrofitInstance: AppModule.ApiInterface
) {

suspend fun getData(): Response<ActivityModel> {
    return retrofitInstance.getActivity()
}
}