package com.example.footballapp.data.model

data class MatchDto(
    val matchDate: Long,
    val matchSummary: MatchSummaryDto,
    val matchTime: Double,
    val stadiumAdress: String,
    val team1: TeamDto,
    val team2: TeamDto
)