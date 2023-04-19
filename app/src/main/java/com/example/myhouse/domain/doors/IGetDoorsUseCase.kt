package com.example.myhouse.domain.doors

import com.example.myhouse.domain.common.TResult
import com.example.myhouse.domain.entity.DoorsModel

interface IGetDoorsUseCase {
    suspend fun getDoorsList(): TResult<List<DoorsModel>>
}