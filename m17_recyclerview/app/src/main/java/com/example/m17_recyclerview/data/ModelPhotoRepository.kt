package com.example.m17_recyclerview.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class ModelPhotoRepository {

    val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY/"

    private val moshi =Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun retrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    fun retrofitInstance(): ApiInterface = retrofit().create(ApiInterface::class.java)

    interface ApiInterface{
        @GET("photos?sol=1000&api_key=DEMO_KEY")
        suspend fun getPhotos():ModelPhotosDto
    }

}