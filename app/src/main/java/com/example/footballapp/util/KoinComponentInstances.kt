package com.example.footballapp.util

import com.example.footballapp.domain.usecase.get_match.GetFootballMatchUseCase
import com.example.footballapp.domain.usecase.get_score.GetHalfTimeScoreUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinComponentInstances : KoinComponent {
    val matchUseCase by inject<GetFootballMatchUseCase>()
    val haltTimeScoreUseCase by inject<GetHalfTimeScoreUseCase>()
}