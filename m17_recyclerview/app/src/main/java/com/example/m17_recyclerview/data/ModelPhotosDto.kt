package com.example.m17_recyclerview.data

import com.example.m17_recyclerview.entity.ModelPhotos
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ModelPhotosDto(
    override val photos: List<ModelPhotos.Photo>
):ModelPhotos
