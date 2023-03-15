package com.example.m19_location.data

import com.example.m19_location.entity.ModelLandmark
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
interface ModelLandmarkDto {
    val landmark: List<ModelLandmark.modelItem>
}
/*
@JsonClass(generateAdapter = true)
class ModelLandmarkDto(
    @Json(name = "kinds")
    override val kinds: String,
    @Json(name = "name")
    override val name: String,
    @Json(name = "osm")
    override val osm: String,
    @Json(name = "point")
    override val point: ModelLandmark.Point,
    @Json(name = "rate")
    override val rate: Int,
    @Json(name = "xid")
    override val xid: String
) : ModelLandmark*/
