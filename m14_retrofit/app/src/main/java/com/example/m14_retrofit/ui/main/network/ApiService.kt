package com.example.m14_retrofit.ui.main.network

import com.example.m14_retrofit.ui.main.network.data.Info
import com.example.m14_retrofit.ui.main.network.data.Result
import com.example.m14_retrofit.ui.main.network.data.UserModel
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://randomuser.me/"


private val moshi = Moshi.Builder()
  //  .add(UserAdapter())
    .add(KotlinJsonAdapterFactory())
    .build()



    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    object RetrofitInstance {
    val searchUserApi: SearchUserApi by lazy { retrofit.create(SearchUserApi::class.java) }

}


interface SearchUserApi {
    @GET("api")
    suspend fun getUser(): Response<UserModel>
}

/*class UserAdapter {
    @ToJson
    fun toJson(card: UserModel): List<Result> {
        return card.results
    }

    @FromJson
    fun fromJson(card: UserModel): Info? {

        val rank = card.info
        if (card.info != null) {
            return card.info
        } else {
            throw JsonDataException("unknown suit: $card")
        }
    }*/

