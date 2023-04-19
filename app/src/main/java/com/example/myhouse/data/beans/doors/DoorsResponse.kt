package com.example.myhouse.data.beans.doors

import com.google.gson.annotations.SerializedName

data class DoorsResponse(
    @SerializedName("data")
    val doors: List<Door>,
    @SerializedName("success")
    val success: Boolean
)