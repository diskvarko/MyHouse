package com.example.myhouse.data.beans.camera

import com.example.myhouse.data.database.CameraEntity
import com.example.myhouse.domain.entity.CameraModel
import com.google.gson.annotations.SerializedName

data class Camera(
    @SerializedName("favorites")
    val favorites: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rec")
    val rec: Boolean,
    @SerializedName("room")
    val room: String,
    @SerializedName("snapshot")
    val snapshot: String
)

fun Camera.mapToCameraEntity(): CameraEntity {
    return CameraEntity(
        favorites = favorites,
        id = id,
        name = name,
        rec = rec,
        room = room,
        snapshot = snapshot
    )
}

fun Camera.mapToCameraModel(): CameraModel {
    return CameraModel(
        favorites = favorites,
        id = id,
        name = name,
        rec = rec,
        room = room,
        snapshot = snapshot
    )
}