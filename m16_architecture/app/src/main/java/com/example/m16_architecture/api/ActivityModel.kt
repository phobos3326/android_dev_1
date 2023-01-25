package com.example.m16_architecture.api
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ActivityModel(
    @Json(name = "accessibility")
    var accessibility: Double,
    @Json(name = "activity")
    var activity: String,
    @Json(name = "key")
    var key: String,
    @Json(name = "link")
    var link: String,
    @Json(name = "participants")
    var participants: Int,
    @Json(name = "price")
    var price: Double,
    @Json(name = "type")
    var type: String
)