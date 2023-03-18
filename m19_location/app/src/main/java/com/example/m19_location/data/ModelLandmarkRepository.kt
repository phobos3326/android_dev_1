package com.example.m19_location.data

import com.example.m19_location.entity.ModelLandmark
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

class ModelLandmarkRepository {

    suspend fun getLandmark(): ModelLandmark{
        return retrofitInstance().getLandmark()
    }


    private val BASE_URL =
        "https://api.opentripmap.com/"

     val logInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

     val httpClientBuilder = OkHttpClient.Builder()
        .addInterceptor(logInterceptor)

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClientBuilder.build())
            .build()
    }

     fun retrofitInstance(): ApiInterface = retrofit().create(ApiInterface::class.java)

    interface ApiInterface {

        @GET("0.1/ru/places/bbox?lon_min=80&lon_max=85&lat_min=52&lat_max=57&src_geom=osm&src_attr=osm&format=geojson&apikey=5ae2e3f221c38a28845f05b61150ee6063873ab9c7bad658e45bfed3")
        suspend fun getLandmark(): ModelLandmark
    }

}


