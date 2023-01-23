package com.example.m16_architecture.ui.main.entity

import dagger.Component
import dagger.Module

@Component
interface UsefulActivity {
    val activity:String
    val type:String
    val participants:Int
    val link:String
    val key:Int
    val accessibility:Int
}