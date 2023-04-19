package com.example.myhouse.data.beans.camera

import com.example.myhouse.data.beans.camera.Data
import com.google.gson.annotations.SerializedName

data class CamerasResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("success")
    val success: Boolean
)