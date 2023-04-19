package com.example.myhouse.domain.doors

import com.example.myhouse.domain.entity.DoorsModel

interface ISetDoorsUseCase {
    suspend fun insert(doors: List<DoorsModel>)
}