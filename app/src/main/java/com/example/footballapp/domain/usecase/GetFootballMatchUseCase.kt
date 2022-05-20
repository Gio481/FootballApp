package com.example.footballapp.domain.usecase

import com.example.footballapp.domain.model.MatchDomain

interface GetFootballMatchUseCase {
    suspend fun getFootballMatch(action: (message: String) -> Unit): MatchDomain?
}