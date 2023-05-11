package com.example.myhouse.data

import com.example.myhouse.data.beans.camera.mapToCameraModel
import com.example.myhouse.data.database.CameraEntity
import com.example.myhouse.data.database.mapToCameraEntity
import com.example.myhouse.data.network.NetworkApi
import com.example.myhouse.domain.ICamerasRepository
import com.example.myhouse.domain.common.TResult
import com.example.myhouse.domain.entity.CameraModel
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CamerasRepository(
    private val networkApi: NetworkApi,
    private val realm: Realm
) : ICamerasRepository {

    override suspend fun getCameras(): TResult<List<CameraModel>> = withContext(Dispatchers.IO) {
        val response = networkApi.getCamerasList()
        if (response.success) {
            val data = mutableListOf<CameraModel>()
            response.data.cameras.forEach {
                data.add(it.mapToCameraModel())
            }
            TResult.Success(data)
        } else {
            TResult.Error(Throwable("Error loading cameras"))
        }
    }

    override suspend fun insert(cameras: List<CameraModel>) {
        realm.write {
            cameras.forEach {
                copyToRealm(it.mapToCameraEntity(), updatePolicy = UpdatePolicy.ALL)
            }
        }
    }

    override suspend fun getCamerasList(): List<CameraEntity> =
        realm.query<CameraEntity>().find()


    override suspend fun setFavorite(entity: CameraEntity) {
        realm.write {
            val item: CameraEntity? =
                query<CameraEntity>("id == $0", entity.id).first().find()
            item?.favorites = entity.favorites
        }
    }

}