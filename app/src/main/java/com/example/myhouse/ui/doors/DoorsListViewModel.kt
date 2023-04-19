package com.example.myhouse.ui.doors

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhouse.asLiveData
import com.example.myhouse.data.database.DoorsEntity
import com.example.myhouse.data.database.mapToCameraModel
import com.example.myhouse.data.database.mapToDoorsModel
import com.example.myhouse.domain.common.TResult
import com.example.myhouse.domain.doors.*
import com.example.myhouse.domain.entity.CameraModel
import com.example.myhouse.domain.entity.DoorsModel
import kotlinx.coroutines.launch

class DoorsListViewModel(
    private val getDoorsUseCase: IGetDoorsUseCase,
    private val setDoorsUseCase: ISetDoorsUseCase,
    private val getDoorsFromDBUseCase: IGetDoorsFromDBUseCase,
    private val setFavoriteItemUseCase: ISetFavoriteItemUseCase,
    private val updateNameUseCase: IUpdateNameUseCase
) : ViewModel() {

    private val _doorsList = MutableLiveData<List<DoorsModel>>()
    val doorsList = _doorsList.asLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _closeLoadingRefresh = MutableLiveData<Unit>()
    val closeLoadingRefresh = _closeLoadingRefresh.asLiveData()

    init {
        viewModelScope.launch {
            _isLoading.value = true
            val list = getDoorsFromDBUseCase.getAll()
            if (list.isEmpty()) {
                updateList()
            } else {
                val results = mutableListOf<DoorsModel>()
                list.forEach {
                    results.add(it.mapToDoorsModel())
                }
                _doorsList.value = results
                _isLoading.value = false
            }
        }
    }

    fun updateList() {
        viewModelScope.launch {
            val result = getDoorsUseCase.getDoorsList()
            //add loader
            when (result) {
                is TResult.Success -> {
                    val list = mutableListOf<DoorsModel>()
                    result.data.forEach {
                        list.add(it)
                    }
                    setDoorsUseCase.insert(list)
                    _doorsList.value = list
                    _isLoading.value = false
                    _closeLoadingRefresh.value = Unit
                }
                is TResult.Error -> {
                    _isLoading.value = false
                }
            }
        }
    }

    fun updateDataBaseList() {
        viewModelScope.launch {
            val list = getDoorsFromDBUseCase.getAll()
            val results = mutableListOf<DoorsModel>()
            list.forEach {
                results.add(it.mapToDoorsModel())
            }
            _doorsList.value = results
        }
    }

    fun onFavoriteClick(entity: DoorsEntity) {
        viewModelScope.launch {
            setFavoriteItemUseCase.setFavorite(entity)
        }
        updateDataBaseList()
    }

    fun editName(entity: DoorsEntity) {
        viewModelScope.launch {
            updateNameUseCase.setName(entity)
        }
        updateDataBaseList()
    }
}