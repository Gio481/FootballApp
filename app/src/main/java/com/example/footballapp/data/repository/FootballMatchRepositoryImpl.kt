package com.example.footballapp.data.repository

import com.example.footballapp.data.mapper.FootballMatchDtoMapper
import com.example.footballapp.data.network.ApiService
import com.example.footballapp.domain.model.MatchDomain
import com.example.footballapp.domain.repository.FootballMatchRepository
import com.example.footballapp.util.Resources
import com.example.footballapp.util.apiDataFetcher

class FootballMatchRepositoryImpl(
    private val dataMapper: FootballMatchDtoMapper,
    private val apiService: ApiService,
) : FootballMatchRepository {

    override suspend fun footballMatch(): Resources<MatchDomain> {
        return apiDataFetcher(dataMapper) { apiService.getFootballMatch() }
    }
}