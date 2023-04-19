package com.example.myhouse.ui.cameras

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhouse.asLiveData
import com.example.myhouse.data.database.mapToCameraEntity
import com.example.myhouse.data.database.mapToCameraModel
import com.example.myhouse.domain.cameras.IGetCamerasFromDBUseCase
import com.example.myhouse.domain.cameras.IGetCamerasUseCase
import com.example.myhouse.domain.cameras.ISetCamerasUseCase
import com.example.myhouse.domain.cameras.ISetFavoriteCamerasItemUseCase
import com.example.myhouse.domain.common.TResult
import com.example.myhouse.domain.entity.CameraModel
import kotlinx.coroutines.launch

class CamerasListViewModel(
    private val getCamerasUseCase: IGetCamerasUseCase,
    private val setCamerasUseCase: ISetCamerasUseCase,
    private val getCamerasFromDBUseCase: IGetCamerasFromDBUseCase,
    private val setFavoriteItemUseCase: ISetFavoriteCamerasItemUseCase
) : ViewModel() {

    private val _camerasList = MutableLiveData<List<CameraModel>>()
    val camerasList = _camerasList.asLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _closeLoadingRefresh = MutableLiveData<Unit>()
    val closeLoadingRefresh = _closeLoadingRefresh.asLiveData()

    init {
        _isLoading.value = true
        viewModelScope.launch {
            val list = getCamerasFromDBUseCase.getAll()
            if (list.isEmpty()) {
                updateListFromNetwork()
            } else {
                val results = mutableListOf<CameraModel>()
                list.forEach {
                    results.add(it.mapToCameraModel())
                }
                _camerasList.value = results
            }
        }
        _isLoading.value = false
    }

    fun updateDataBaseList() {
        viewModelScope.launch {
            val list = getCamerasFromDBUseCase.getAll()
            val results = mutableListOf<CameraModel>()
            list.forEach {
                results.add(it.mapToCameraModel())
            }
            _camerasList.value = results
        }
    }

    fun updateListFromNetwork() {
        viewModelScope.launch {
            val result = getCamerasUseCase.getCameras()
            when (result) {
                is TResult.Success -> {
                    val list = mutableListOf<CameraModel>()
                    result.data.forEach {
                        list.add(it)
                    }
                    setCamerasUseCase.insert(list)
                    _camerasList.value = list
                    _closeLoadingRefresh.value = Unit
                    _isLoading.value = false
                }
                is TResult.Error -> {
                    _isLoading.value = false
                }
            }
        }
    }

    fun onFavoriteClick(position: Int) {
        val entity = camerasList.value?.get(position)?.mapToCameraEntity()
        entity?.favorites = !entity?.favorites!!
        viewModelScope.launch {
            setFavoriteItemUseCase.setFavorite(entity)
        }
    }
}