package com.example.m16_architecture.entity

import com.squareup.moshi.Json
import dagger.Component


interface UsefulActivity {
    var accessibility: Double
    var activity: String
    var key: String
    var link: String
    var participants: Int
    var price: Double
    var type: String
}