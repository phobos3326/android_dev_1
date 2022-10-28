package com.example.m14_retrofit.ui.main.network

import com.example.m14_retrofit.ui.main.network.data.Test

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers


private const val BASE_URL = "https://randomuser.me/"

/*
object RetrofitInstance{
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val searchUserApi:ApiService= retrofit.create(ApiService::class.java)
}
*/

object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }
}


interface ApiService {
    @Headers(
        "Accept:application/json",
        "Content-type:application/json"
    )
    @GET("api")
    suspend fun getPhoto(): Call<Test>
}

/*
object UserApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
*/
