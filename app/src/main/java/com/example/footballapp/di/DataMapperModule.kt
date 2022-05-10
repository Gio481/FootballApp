package com.example.footballapp.di

import com.example.footballapp.data.mapper.*
import org.koin.dsl.module

val dataMapperModule = module {
    single { ActionDtoMapper(get()) }
    single { FootballMatchDtoMapper(get(), get()) }
    single { MatchSummaryDtoMapper(get()) }
    single { PlayerDtoMapper() }
    single { TeamActionDtoMapper(get()) }
    single { TeamDtoMapper() }
}