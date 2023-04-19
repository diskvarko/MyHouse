package com.example.myhouse.data.database

import com.example.myhouse.data.beans.camera.Camera
import com.example.myhouse.domain.entity.CameraModel
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.realmSetOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class CameraEntity(
    @PrimaryKey var id: Int,
    var favorites: Boolean,
    var name: String,
    var rec: Boolean,
    var room: String,
    var snapshot: String
) : RealmObject {
    constructor() : this(
        id = 0,
        favorites = false,
        name = "",
        rec = false,
        room = "",
        snapshot = ""
    )
}

fun CameraModel.mapToCameraEntity(): CameraEntity {
    return CameraEntity(
        favorites = favorites,
        id = id,
        name = name,
        rec = rec,
        room = room ?: "",
        snapshot = snapshot ?: ""
    )
}

fun CameraEntity.mapToCameraModel(): CameraModel {
    return CameraModel(
        favorites = favorites,
        id = id,
        name = name,
        rec = rec,
        room = room ?: "",
        snapshot = snapshot ?: ""
    )
}
