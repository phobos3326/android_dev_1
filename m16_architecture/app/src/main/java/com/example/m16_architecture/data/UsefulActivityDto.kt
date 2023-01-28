package com.example.m16_architecture.data

import com.example.m16_architecture.entity.UsefulActivity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsefulActivityDto(
    override var accessibility: Double,
    override var activity: String,
    override var key: String,
    override var link: String,
    override var participants: Int,
    override var price: Double,
    override var type: String
) : UsefulActivity
