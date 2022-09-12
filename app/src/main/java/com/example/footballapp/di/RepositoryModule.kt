package com.example.footballapp.di

import com.example.footballapp.data.repository.FootballMatchRepositoryImpl
import com.example.footballapp.domain.repository.FootballMatchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<FootballMatchRepository> { FootballMatchRepositoryImpl(get(), get()) }
}