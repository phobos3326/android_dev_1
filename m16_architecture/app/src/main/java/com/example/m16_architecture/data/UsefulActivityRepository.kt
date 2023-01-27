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

@Module
@InstallIn(SingletonComponent::class)
class UsefulActivityRepository {



    @Provides
    fun providesBaseUrl() = "https://www.boredapi.com/"


    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideRetrofit(BASE_URL: String) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun retrofitInstanse(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }



    interface ApiInterface {
        @GET("api/activity/")
        suspend fun getActivity(): Response<UsefulActivityDto>

    }

}