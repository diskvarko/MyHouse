package com.example.myhouse.di

import androidx.annotation.Keep
import com.example.myhouse.data.CamerasRepository
import com.example.myhouse.data.DoorsRepository
import com.example.myhouse.domain.ICamerasRepository
import com.example.myhouse.domain.IDoorsRepository
import org.koin.dsl.module

@Keep
val repositoryModule = module {
    factory<ICamerasRepository> {
        CamerasRepository(
            get(), get()
        )
    }
    factory<IDoorsRepository> {
        DoorsRepository(
            get(), get()
        )
    }
}