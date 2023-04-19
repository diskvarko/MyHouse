package com.example.myhouse.di

import androidx.annotation.Keep
import com.example.myhouse.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.dsl.module

@Keep
val repositoryModule = module {
    factory<ICamerasRepository> {
        CamerasRepository(
            get(), get(),
            CoroutineScope(Job() + Dispatchers.IO)
        )
    }
    factory<IDoorsRepository> {
        DoorsRepository(
            get(), get(),
            CoroutineScope(Job() + Dispatchers.IO)
        )
    }
}