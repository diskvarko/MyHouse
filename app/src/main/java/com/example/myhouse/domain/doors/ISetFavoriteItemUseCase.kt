package com.example.myhouse.domain.doors

import com.example.myhouse.data.database.DoorsEntity

interface ISetFavoriteItemUseCase {
    suspend fun setFavorite(entity: DoorsEntity)
}