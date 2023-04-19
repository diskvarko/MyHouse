package com.example.myhouse.di

import androidx.annotation.Keep
import com.example.myhouse.data.database.CameraEntity
import com.example.myhouse.data.database.DoorsEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

@Keep
val databaseModule = module {
    single {
        val configuration = RealmConfiguration
            .Builder(
                schema = setOf(
                    DoorsEntity::class,
                    CameraEntity::class
                )
            )
            .build()
        Realm.open(configuration)
    }
}