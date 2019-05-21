package com.example.hotnews.di

import com.example.hotnews.network.ConnectionManager
import com.example.hotnews.network.ConnectionManagerImp
import org.koin.dsl.bind
import org.koin.dsl.module

val connectionManagerModule = module {
    factory { ConnectionManagerImp(get()) } bind ConnectionManager::class
}