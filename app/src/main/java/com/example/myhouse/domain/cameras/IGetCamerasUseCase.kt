package com.example.myhouse.domain.cameras

import com.example.myhouse.domain.common.TResult
import com.example.myhouse.domain.entity.CameraModel

interface IGetCamerasUseCase {
    suspend fun getCameras(): TResult<List<CameraModel>>
}