package com.example.m17_recyclerview.entity


interface  ModelPhotos {
    val photos: List<Photo>

    data class Photo(

        val camera: Camera,
        val earthDate: String,
        val id: Int,
        val imgSrc: String,
        val rover: Rover,
        val sol: Int
    ) {

        data class Camera(
            val fullName: String,
            val id: Int,
            val name: String,
            val roverId: Int
        )


        data class Rover(
            val id: Int,
            val landingDate: String,
            val launchDate: String,
            val name: String,
            val status: String
        )
    }
}