package com.example.myhouse.domain.doors

import com.example.myhouse.domain.IDoorsRepository
import com.example.myhouse.data.database.DoorsEntity

class GetDoorsFromDBUseCase(private val repository: IDoorsRepository) : IGetDoorsFromDBUseCase {
    override suspend fun getAll(): List<DoorsEntity> {
        return repository.getAll()
    }
}