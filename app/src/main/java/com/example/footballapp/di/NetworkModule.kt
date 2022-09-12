package com.example.footballapp.di

import com.example.footballapp.data.network.provideGsonConvertorFactory
import com.example.footballapp.data.network.provideHttpClient
import com.example.footballapp.data.network.provideRetrofit
import com.example.footballapp.data.network.provideUserApi
import org.koin.dsl.module

val networkModule = module {
    single { provideUserApi(get()) }
    single { provideHttpClient() }
    single { provideGsonConvertorFactory() }
    single { provideRetrofit() }
}