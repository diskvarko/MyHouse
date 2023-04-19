package com.example.myhouse.domain.cameras

import com.example.myhouse.domain.ICamerasRepository
import com.example.myhouse.data.database.CameraEntity

class GetCamerasFromDBUseCase(val repository: ICamerasRepository) : IGetCamerasFromDBUseCase {
    override suspend fun getAll(): List<CameraEntity> {
        return repository.getCamerasList()
    }
}