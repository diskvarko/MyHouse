package com.example.myhouse.domain.entity


data class CameraModel(
    val favorites: Boolean,
    val id: Int,
    val name: String,
    val rec: Boolean,
    val room: String?,
    val snapshot: String?
)

