package com.example.myhouse.di

import android.content.Context
import androidx.annotation.Keep
import com.example.myhouse.data.network.NetworkApi
import com.example.myhouse.data.network.RetrofitBuilder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

@Keep
val networkModule = module {
    single { GsonBuilder().create() }
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        val cacheDir = File((get<Context>()).cacheDir, "http")
        val cache = Cache(
            cacheDir,
            10 * 1024 * 1024 // 10 MB
        )
        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("http://cars.cprogroup.ru/api/rubetek/")
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .build()
    }

    single {
        get<Retrofit>().create(NetworkApi::class.java)
    }

}