package com.example.myhouse.di

import androidx.annotation.Keep


@Keep
val applicationModules = listOf(
    networkModule,
    databaseModule,
    repositoryModule,
    useCasesModule,
    viewModelModule
)