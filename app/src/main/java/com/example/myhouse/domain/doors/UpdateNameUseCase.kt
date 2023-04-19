package com.example.myhouse.domain.doors

import com.example.myhouse.data.IDoorsRepository
import com.example.myhouse.data.database.DoorsEntity

class UpdateNameUseCase(val repository: IDoorsRepository): IUpdateNameUseCase {
    override suspend fun setName(entity: DoorsEntity) {
        repository.updateName(entity)
    }
}