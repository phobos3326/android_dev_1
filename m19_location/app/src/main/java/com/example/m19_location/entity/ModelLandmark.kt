package com.example.m19_location.entity


import com.example.m19_location.data.ModelLandmarksDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ModelLandmark(
    @Json(name = "features")
    override val features: List<Feature>,
    @Json(name = "type")
    override val type: String
) : ModelLandmarksDto {
    @JsonClass(generateAdapter = true)
    data class Feature(
        @Json(name = "geometry")
        val geometry: Geometry,
        @Json(name = "id")
        val id: String,
        @Json(name = "properties")
        val properties: Properties,
        @Json(name = "type")
        val type: String
    ) {
        @JsonClass(generateAdapter = true)
        data class Geometry(
            @Json(name = "coordinates")
            val coordinates: List<Double>,
            @Json(name = "type")
            val type: String
        )

        @JsonClass(generateAdapter = true)
        data class Properties(
            @Json(name = "kinds")
            val kinds: String,
            @Json(name = "name")
            val name: String,
            @Json(name = "osm")
            val osm: String,
            @Json(name = "rate")
            val rate: Int,
            @Json(name = "xid")
            val xid: String
        )
    }
}