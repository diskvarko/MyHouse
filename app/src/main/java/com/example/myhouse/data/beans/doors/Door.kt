package com.example.myhouse.data.beans.doors

import com.google.gson.annotations.SerializedName

data class Door(
    @SerializedName("favorites")
    val favorites: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rec")
    val room: String,
    @SerializedName("snapshot")
    val snapshot: String
)