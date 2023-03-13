package com.example.m19_location.data

import com.example.m19_location.entity.ModelLandmark
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class ModelLandmarkRepository {

    suspend fun getLandmark(): ModelLandmark {
        return retrofitInstance().getLAndmark()
    }


    private val BASE_URL =
        "https://api.opentripmap.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    fun retrofitInstance(): ApiInterface = retrofit().create(ApiInterface::class.java)

    interface ApiInterface {
        @GET("0.1/ru/places/bbox?lon_min=50&lon_max=55&lat_min=80&lat_max=85&src_geom=osm&src_attr=osm&format=json&apikey=5ae2e3f221c38a28845f05b61150ee6063873ab9c7bad658e45bfed3")
        suspend fun getLAndmark(): ModelLandmark
    }

}


