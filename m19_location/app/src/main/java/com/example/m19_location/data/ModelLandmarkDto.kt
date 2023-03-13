package com.example.m19_location.data

import com.example.m19_location.entity.ModelLandmark
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
interface ModelLandmarkDto {
    val landmark: List<ModelLandmark.ModelLandmarkItem>
}