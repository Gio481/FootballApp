package com.example.footballapp.di

import com.example.footballapp.data.mapper.*
import com.example.footballapp.domain.mapper.*
import com.example.footballapp.presentation.match.adapter.helper.MatchAttributesCreator
import org.koin.dsl.module

val dataMapperModule = module {
    single { ActionDtoMapper(get()) }
    single { FootballMatchDtoMapper(get(), get()) }
    single { MatchSummaryDtoMapper(get()) }
    single { PlayerDtoMapper() }
    single { TeamActionDtoMapper(get()) }
    single { TeamDtoMapper() }
    single { TeamActionDomainMapper(get()) }
    single { ActionDomainMapper(get()) }
    single { PlayerDomainMapper() }
    single { SummaryDomainMapper(get()) }
    single { SubstitutionActionDomainMapper() }
    single { RegularActionAttrsDomainMapper() }
    single { CustomAttrsDomainMapper(get(), get()) }
    single { MatchAttributesCreator() }
}