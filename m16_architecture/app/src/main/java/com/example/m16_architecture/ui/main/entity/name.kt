package com.example.m16_architecture.ui.main.entity


import com.google.gson.annotations.SerializedName

data class name(
    @SerializedName("accessibility")
    val accessibility: Int,
    @SerializedName("activity")
    val activity: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("participants")
    val participants: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("type")
    val type: String
)