package com.example.myhouse.data.network

import com.example.myhouse.data.beans.camera.CamerasResponse
import com.example.myhouse.data.beans.doors.DoorsResponse
import com.example.myhouse.domain.common.TResult
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {
    @GET("cameras/")
    suspend fun getCamerasList() : CamerasResponse

    @GET("doors/")
    suspend fun getDoorsList() : DoorsResponse
}