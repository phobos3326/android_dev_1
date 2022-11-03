package com.example.m14_retrofit.ui.main.network

import com.example.m14_retrofit.ui.main.network.data.UserModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://randomuser.me/"



private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


object RetrofitInstance{
    private val retrofit =Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val searchUserApi:SearchUserApi = retrofit.create(SearchUserApi::class.java)

}


interface SearchUserApi{
    @GET("api")
    fun getUser():Call<UserModel>
}
