package com.example.m16_architecture.data

import com.example.m16_architecture.entity.UsefulActivity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsefulActivityDto(
   @Json override var accessibility: Double,
   @Json  override var activity: String,
   @Json override var key: String,
   @Json override var link: String,
   @Json override var participants: Int,
   @Json override var price: Double,
   @Json override var type: String
) : UsefulActivity
