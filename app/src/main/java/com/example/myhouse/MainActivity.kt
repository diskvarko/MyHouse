package com.example.myhouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myhouse.di.applicationModules
import io.realm.*
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(applicationModules)
        }
    }

}