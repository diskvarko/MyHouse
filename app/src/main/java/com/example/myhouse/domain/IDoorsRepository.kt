package com.example.myhouse.domain

import com.example.myhouse.data.database.DoorsEntity
import com.example.myhouse.domain.common.TResult
import com.example.myhouse.domain.entity.DoorsModel

interface IDoorsRepository {

    suspend fun getDoorsList(): TResult<List<DoorsModel>>

    suspend fun insert(door: List<DoorsModel>)

    suspend fun getAll(): List<DoorsEntity>

    suspend fun setFavorite(entity: DoorsEntity)

    suspend fun updateName(entity: DoorsEntity)
}