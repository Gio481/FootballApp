package com.example.footballapp.domain.repository

import com.example.footballapp.domain.model.MatchDomain
import com.example.footballapp.util.Resources

interface FootballMatchRepository {
    suspend fun footballMatch():Resources<MatchDomain>
}