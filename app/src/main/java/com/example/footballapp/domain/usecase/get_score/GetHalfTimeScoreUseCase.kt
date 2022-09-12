package com.example.footballapp.domain.usecase.get_score

import com.example.footballapp.domain.model.MatchDomain

interface GetHalfTimeScoreUseCase {
    fun getHalfTimeScore(halfTime: IntRange, match: MatchDomain?): String
}