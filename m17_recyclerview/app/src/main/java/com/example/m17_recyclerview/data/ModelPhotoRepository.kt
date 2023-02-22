package com.example.m17_recyclerview.data


import com.example.m17_recyclerview.entity.ModelPhotos
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

@Module
class ModelPhotoRepository @Inject constructor() {

    @Provides
    suspend fun getMarsPhotos(): ModelPhotos {
        delay(2000)
        return retrofitInstance().getPhotos()
    }

    @Provides
    suspend fun getPagedPhotos(page: Int):ModelPhotos{
        return retrofitInstance().pagedPhotos(page)
    }


    private val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"

    private val moshi =Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun retrofit():Retrofit?{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    fun retrofitInstance(): ApiInterface = retrofit()!!.create(ApiInterface::class.java)

    interface ApiInterface{
        @GET("photos?sol=1000&api_key=g3X6YmBhYNp5chAfNv1BxSremKDOkJwA74LJUuBV")
        suspend fun getPhotos():ModelPhotos

        @GET("photos?sol=1000&page=2&api_key=g3X6YmBhYNp5chAfNv1BxSremKDOkJwA74LJUuBV")
       suspend fun pagedPhotos(@Query("page")page:Int):ModelPhotos
    }

}