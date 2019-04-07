package com.example.hotnews.di

import com.example.hotnews.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { NewsRepository(get()) }
}