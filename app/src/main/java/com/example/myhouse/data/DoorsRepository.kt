package com.example.myhouse.data

import com.example.myhouse.data.database.DoorsEntity
import com.example.myhouse.data.database.mapToDoorsEntity
import com.example.myhouse.data.network.NetworkApi
import com.example.myhouse.domain.IDoorsRepository
import com.example.myhouse.domain.common.TResult
import com.example.myhouse.domain.entity.DoorsModel
import com.example.myhouse.domain.entity.mapToDoorsModel
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DoorsRepository(
    private val networkApi: NetworkApi,
    private val realm: Realm,
    private val applicationScope: CoroutineScope
) : IDoorsRepository {

    override suspend fun getDoorsList(): TResult<List<DoorsModel>> = withContext(Dispatchers.IO) {
        val response = networkApi.getDoorsList()
        if (response.success) {
            val data = mutableListOf<DoorsModel>()
            response.doors.forEach {
                data.add(it.mapToDoorsModel())
            }
            TResult.Success(data)
        } else {
            TResult.Error(Throwable("Error loading door"))
        }
    }

    override suspend fun insert(door: List<DoorsModel>) {
        applicationScope.launch(Dispatchers.IO) {
            realm.write {
                door.forEach {
                    copyToRealm(it.mapToDoorsEntity(), updatePolicy = UpdatePolicy.ALL)
                }
            }
        }
    }

    override suspend fun getAll(): List<DoorsEntity> {
        return realm.query<DoorsEntity>().find()
    }

    override suspend fun setFavorite(entity: DoorsEntity) {
       applicationScope.launch(Dispatchers.IO) {
           realm.write {
                   val item: DoorsEntity? =
                       query<DoorsEntity>("id == $0", entity.id).first().find()
                   item?.favorites = entity.favorites
           }
       }
    }

    override suspend fun updateName(entity: DoorsEntity) {
        applicationScope.launch(Dispatchers.IO) {
            realm.write {
                val item: DoorsEntity? =
                    query<DoorsEntity>("id == $0", entity.id).first().find()
                item?.name = entity.name
            }
        }
    }

}