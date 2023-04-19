package com.example.myhouse.domain.cameras

import com.example.myhouse.domain.entity.CameraModel

interface ISetCamerasUseCase {
    suspend fun insert(cameras: List<CameraModel>)
}