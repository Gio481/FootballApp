package com.example.footballapp.domain.usecase

import com.example.footballapp.domain.model.MatchDomain

interface GetFootballMatchUseCase {
    suspend fun getFootballMatch(action: (message: String) -> Unit): MatchDomain?
    suspend fun getFirstHalfScore(action: (message: String) -> Unit): String
    suspend fun getSecondHalfScore(action: (message: String) -> Unit): String
}