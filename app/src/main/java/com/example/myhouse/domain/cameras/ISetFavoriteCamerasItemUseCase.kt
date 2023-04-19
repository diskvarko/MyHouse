package com.example.myhouse.domain.cameras

import com.example.myhouse.data.database.CameraEntity

interface ISetFavoriteCamerasItemUseCase {
    suspend fun setFavorite(entity: CameraEntity)
}