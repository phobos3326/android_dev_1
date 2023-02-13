package com.example.m17_recyclerview.data





import com.example.m17_recyclerview.entity.ModelPhotos
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
interface ModelPhotosDto {
    val photos: List<ModelPhotos.Photo>
}