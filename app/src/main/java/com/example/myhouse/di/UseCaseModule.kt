package com.example.myhouse.di

import androidx.annotation.Keep
import com.example.myhouse.domain.cameras.*
import com.example.myhouse.domain.cameras.ISetFavoriteCamerasItemUseCase
import com.example.myhouse.domain.cameras.SetFavoriteCamerasItemUseCase
import com.example.myhouse.domain.doors.*
import org.koin.dsl.module

@Keep
val useCasesModule = module {
    factory<IGetCamerasUseCase> {
        GetCamerasListUseCase(
            repository = get()
        )
    }

    factory<IGetDoorsUseCase> {
        GetDoorsUseCase(
            repository = get()
        )
    }

    factory<ISetCamerasUseCase> {
        SetCamerasUseCase(
            repository = get()
        )
    }

    factory<ISetDoorsUseCase> {
        SetDoorsUseCase(
            repository = get()
        )
    }

    factory<IGetCamerasFromDBUseCase> {
        GetCamerasFromDBUseCase(
            repository = get()
        )
    }

    factory<IGetDoorsFromDBUseCase> {
        GetDoorsFromDBUseCase(
            repository = get()
        )
    }

    factory<ISetFavoriteItemUseCase> {
        SetFavoriteItemUseCase(
            repository = get()
        )
    }

    factory<ISetFavoriteCamerasItemUseCase> {
        SetFavoriteCamerasItemUseCase(
            repository = get()
        )
    }

    factory<IUpdateNameUseCase> {
        UpdateNameUseCase(
            repository = get()
        )
    }
}