package com.example.myhouse.domain.cameras

import com.example.myhouse.domain.ICamerasRepository
import com.example.myhouse.data.database.CameraEntity

class SetFavoriteCamerasItemUseCase(val repository: ICamerasRepository) : ISetFavoriteCamerasItemUseCase {
    override suspend fun setFavorite(entity: CameraEntity) {
        repository.setFavorite(entity)
    }
}