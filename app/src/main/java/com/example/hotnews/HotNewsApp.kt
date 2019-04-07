package com.example.hotnews

import android.app.Application
import com.example.hotnews.di.remoteDatasourceModule
import com.example.hotnews.di.repositoryModule
import com.example.hotnews.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HotNewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HotNewsApp)
            modules(remoteDatasourceModule, repositoryModule, viewModelModule)
        }
    }
}