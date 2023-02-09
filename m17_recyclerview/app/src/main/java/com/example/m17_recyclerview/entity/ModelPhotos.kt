package com.example.m17_recyclerview.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ModelPhotos(val photos: List<Photo>) {

    @JsonClass(generateAdapter = true)
    data class Photo(
        @Json(name = "camera")
        val camera: Camera,
        @Json(name = "earth_date")
        val earthDate: String,
        @Json(name = "id")
        val id: Int,
        @Json(name = "img_src")
        val imgSrc: String,
        @Json(name = "rover")
        val rover: Rover,
        @Json(name = "sol")
        val sol: Int
    ) {
        @JsonClass(generateAdapter = true)
        data class Camera(
            @Json(name = "full_name")
            val fullName: String,
            @Json(name = "id")
            val id: Int,
            @Json(name = "name")
            val name: String,
            @Json(name = "rover_id")
            val roverId: Int
        )

        @JsonClass(generateAdapter = true)
        data class Rover(
            @Json(name = "id")
            val id: Int,
            @Json(name = "landing_date")
            val landingDate: String,
            @Json(name = "launch_date")
            val launchDate: String,
            @Json(name = "name")
            val name: String,
            @Json(name = "status")
            val status: String
        )
    }
}