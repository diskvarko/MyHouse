package com.example.myhouse.domain.cameras

import com.example.myhouse.data.ICamerasRepository
import com.example.myhouse.domain.common.TResult
import com.example.myhouse.domain.entity.CameraModel

class GetCamerasListUseCase(
    private val repository: ICamerasRepository
) : IGetCamerasUseCase {

    override suspend fun getCameras(): TResult<List<CameraModel>> {
        return repository.getCameras()
    }
}