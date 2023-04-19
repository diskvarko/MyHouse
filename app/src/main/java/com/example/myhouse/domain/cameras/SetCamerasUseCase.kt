package com.example.myhouse.domain.cameras

import com.example.myhouse.data.ICamerasRepository
import com.example.myhouse.domain.entity.CameraModel

class SetCamerasUseCase(private val repository: ICamerasRepository) : ISetCamerasUseCase {
    override suspend fun insert(cameras: List<CameraModel>) {
        repository.insert(cameras)
    }
}