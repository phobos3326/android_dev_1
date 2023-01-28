package com.example.m16_architecture.data


import com.example.m16_architecture.entity.UsefulActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Singleton


class UsefulActivityRepository {


    suspend fun getUsefulActivity(): UsefulActivity {
        return retrofitInstanse().getActivity()
    }

    val BASE_URL = "https://www.boredapi.com/"


    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun retrofit(): Retrofit? {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

  private fun retrofitInstanse(): ApiInterface = retrofit()!!.create(ApiInterface::class.java)


interface ApiInterface {
    @GET("api/activity/")
    suspend fun getActivity(): UsefulActivityDto
}

}