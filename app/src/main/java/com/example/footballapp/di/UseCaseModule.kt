package com.example.footballapp.di

import com.example.footballapp.domain.usecase.GetFootballMatchUseCase
import com.example.footballapp.domain.usecase.GetFootballMatchUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<GetFootballMatchUseCase> { GetFootballMatchUseCaseImpl(get()) }
}