package com.example.footballapp.di

import com.example.footballapp.domain.usecase.get_match.GetFootballMatchUseCase
import com.example.footballapp.domain.usecase.get_match.GetFootballMatchUseCaseImpl
import com.example.footballapp.domain.usecase.get_score.GetHalfTimeScoreUseCase
import com.example.footballapp.domain.usecase.get_score.GetHalfTimeScoreUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<GetFootballMatchUseCase> { GetFootballMatchUseCaseImpl(get()) }
    single<GetHalfTimeScoreUseCase> { GetHalfTimeScoreUseCaseImpl() }
}