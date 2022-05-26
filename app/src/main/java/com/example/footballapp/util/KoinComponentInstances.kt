package com.example.footballapp.util

import com.example.footballapp.domain.usecase.GetFootballMatchUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinComponentInstances:KoinComponent {
    val matchUseCase by inject<GetFootballMatchUseCase>()
}