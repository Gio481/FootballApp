package com.example.footballapp.domain.model

data class MatchDomain(
    val matchDate: Long,
    val matchTime: Double,
    val stadiumAddress: String,
    val summary: List<SummaryDomain>,
    val team1: TeamDomain,
    val team2: TeamDomain,
)