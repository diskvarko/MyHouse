package com.example.myhouse.data.database

import com.example.myhouse.data.beans.doors.Door
import com.example.myhouse.domain.entity.DoorsModel
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class DoorsEntity(
    @PrimaryKey var id: Int,
    var favorites: Boolean,
    var name: String,
    var room: String,
    var snapshot: String
) : RealmObject {
    constructor() : this(
        id = 0,
        favorites = false,
        name = "",
        room = "",
        snapshot = ""
    )
}

fun DoorsModel.mapToDoorsEntity(): DoorsEntity {
    return DoorsEntity(
        favorites = favorites,
        id = id,
        name = name,
        room = room ?: "",
        snapshot = snapshot ?: ""
    )
}

fun DoorsEntity.mapToDoorsModel(): DoorsModel {
    return DoorsModel(
        favorites = favorites,
        id = id,
        name = name,
        room = room ?: "",
        snapshot = snapshot ?: ""
    )
}