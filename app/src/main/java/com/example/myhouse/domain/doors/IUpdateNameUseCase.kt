package com.example.myhouse.domain.doors

import com.example.myhouse.data.database.DoorsEntity

interface IUpdateNameUseCase {
    suspend fun setName(entity: DoorsEntity)
}