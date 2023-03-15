package com.example.m19_location.entity


import android.graphics.Point
import com.example.m19_location.data.ModelLandmarkDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ModelLandmark(
    @JsonClass(generateAdapter = true)
    val model: ArrayList<modelItem>
) {
    @JsonClass(generateAdapter = true)
    data class modelItem(
        @Json(name = "kinds")
        val kinds: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "osm")
        val osm: String,
        @Json(name = "point")
        val point: Point,
        @Json(name = "rate")
        val rate: Int,
        @Json(name = "xid")
        val xid: String
    ) {
        @JsonClass(generateAdapter = true)
        data class Point(
            @Json(name = "lat")
            val lat: Double,
            @Json(name = "lon")
            val lon: Double
        )
    }
}

/*@JsonClass(generateAdapter = true)
class ModelLandmark(override val landmark: List<modelItem>):ModelLandmarkDto {
    @JsonClass(generateAdapter = true)
    data class modelItem(

        @Json(name = "kinds")
        val kinds: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "osm")
        val osm: String,
        @Json(name = "point")
        val point: Point,
        @Json(name = "rate")
        val rate: Int,
        @Json(name = "xid")
    val xid: String
    ) {
        @JsonClass(generateAdapter = true)
        data class Point(
            @Json(name = "lat")
            val lat: Double,
            @Json(name = "lon")
            val lon: Double
        )
    }



}*/
/*
@JsonClass(generateAdapter = true)
interface ModelLandmark {
    @Json(name = "kinds")
    val kinds: String
    @Json(name = "name")
    val name: String
    @Json(name = "osm")
    val osm: String
    @Json(name = "point")
    val point: Point
    @Json(name = "rate")
    val rate: Int
    @Json(name = "xid")
    val xid: String

    @JsonClass(generateAdapter = true)
    interface Point {
        @Json(name = "lat")
        val lat: Double

        @Json(name = "lon")
        val lon: Double
    }
}*/
