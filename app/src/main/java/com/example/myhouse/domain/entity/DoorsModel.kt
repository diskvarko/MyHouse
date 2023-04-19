package com.example.myhouse.domain.entity

import com.example.myhouse.data.beans.doors.Door

data class DoorsModel(
    val favorites: Boolean,
    val id: Int,
    val name: String,
    val room: String?,
    val snapshot: String?
)

fun Door.mapToDoorsModel(): DoorsModel {
    return DoorsModel(
        favorites = favorites,
        id = id,
        name = name,
        room = room,
        snapshot = snapshot
    )
}