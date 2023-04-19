package com.example.myhouse.data

import com.example.myhouse.data.database.CameraEntity
import com.example.myhouse.domain.common.TResult
import com.example.myhouse.domain.entity.CameraModel

interface ICamerasRepository {
    suspend fun getCameras(): TResult<List<CameraModel>>

    suspend fun insert(cameras: List<CameraModel>)

    suspend fun getCamerasList(): List<CameraEntity>

    suspend fun setFavorite(entity: CameraEntity)
}