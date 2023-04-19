package com.example.myhouse.domain.doors

import com.example.myhouse.data.database.DoorsEntity

interface IGetDoorsFromDBUseCase {
    suspend fun getAll(): List<DoorsEntity>
}