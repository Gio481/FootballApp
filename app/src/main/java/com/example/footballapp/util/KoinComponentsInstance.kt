package com.example.footballapp.util

import com.example.footballapp.domain.mapper.CustomAttrsDomainMapper
import com.example.footballapp.domain.mapper.SummaryDomainMapper
import com.example.footballapp.presentation.match.adapter.helper.MatchAttributesCreator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinComponentsInstance : KoinComponent {
    val mapper by inject<SummaryDomainMapper>()
    val customAttrsMapper by inject<CustomAttrsDomainMapper>()
    val customAttrsCreator by inject<MatchAttributesCreator>()
}