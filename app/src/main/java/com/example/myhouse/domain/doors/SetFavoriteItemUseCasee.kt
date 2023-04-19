package com.example.myhouse.domain.doors

import com.example.myhouse.data.IDoorsRepository
import com.example.myhouse.data.database.DoorsEntity

class SetFavoriteItemUseCase(val repository: IDoorsRepository) : ISetFavoriteItemUseCase {
    override suspend fun setFavorite(entity: DoorsEntity) {
        repository.setFavorite(entity)
    }
}