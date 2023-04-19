package com.example.myhouse.domain.doors

import com.example.myhouse.data.IDoorsRepository
import com.example.myhouse.domain.entity.DoorsModel

class SetDoorsUseCase(private val repository: IDoorsRepository) : ISetDoorsUseCase {
    override suspend fun insert(doors: List<DoorsModel>) {
        repository.insert(doors)
    }
}