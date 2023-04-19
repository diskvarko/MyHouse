package com.example.myhouse.domain.doors

import com.example.myhouse.domain.IDoorsRepository
import com.example.myhouse.domain.common.TResult
import com.example.myhouse.domain.entity.DoorsModel

class GetDoorsUseCase(
    private val repository: IDoorsRepository
): IGetDoorsUseCase {

    override suspend fun getDoorsList(): TResult<List<DoorsModel>> {
        return repository.getDoorsList()
    }
}