package com.example.myhouse.domain.cameras

import com.example.myhouse.data.database.CameraEntity

interface IGetCamerasFromDBUseCase {
    suspend fun getAll(): List<CameraEntity>
}