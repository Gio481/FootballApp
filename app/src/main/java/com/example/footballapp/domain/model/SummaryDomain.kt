package com.example.footballapp.domain.model

data class SummaryDomain(
    val actionTime: String,
    val team1Action: List<TeamActionDomain>,
    val team2Action: List<TeamActionDomain>,
)