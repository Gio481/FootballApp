package com.example.footballapp.domain.usecase

import com.example.footballapp.domain.model.MatchDomain
import com.example.footballapp.domain.repository.FootballMatchRepository
import com.example.footballapp.util.repositoryDataFetcher

class GetFootballMatchUseCaseImpl(private val repository: FootballMatchRepository) :
    GetFootballMatchUseCase {

    override suspend fun getFootballMatch(action: (message: String) -> Unit): MatchDomain? {
        return repositoryDataFetcher({ repository.footballMatch() }, { action(it) })
    }
}