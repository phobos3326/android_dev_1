package com.example.m14_retrofit.ui.main.network.data

import com.example.m14_retrofit.ui.main.network.data.Result
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserModel(
    @Json(name = "info") val info:Test,
    @Json(name = "result") val result: List<Result>
)
