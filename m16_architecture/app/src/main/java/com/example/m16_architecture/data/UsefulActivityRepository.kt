package com.example.m16_architecture.data


import com.example.m16_architecture.entity.UsefulActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Inject


@Module
class UsefulActivityRepository @Inject constructor() {

    @Provides
    suspend fun provideGetUsefulActivity(): UsefulActivity {
        return provideRetrofitInstanse().getActivity()
    }

    val BASE_URL = "https://www.boredapi.com/"


    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
     fun provideRetrofit(): Retrofit? {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
     fun provideRetrofitInstanse(): ApiInterface = provideRetrofit()!!.create(ApiInterface::class.java)


    interface ApiInterface {
        @GET("api/activity/")
        suspend fun getActivity(): UsefulActivityDto
    }

}