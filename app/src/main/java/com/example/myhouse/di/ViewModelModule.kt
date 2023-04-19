package com.example.myhouse.di

import androidx.annotation.Keep
import com.example.myhouse.ui.cameras.CamerasListViewModel
import com.example.myhouse.ui.doors.DoorsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Keep
val viewModelModule = module() {
    viewModel {
        CamerasListViewModel (get(), get(), get(), get())
    }

    viewModel {
        DoorsListViewModel(get(), get(), get(), get(), get())
    }
}