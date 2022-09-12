package com.example.footballapp.data.model

data class SummaryDto(
    val actionTime: String,
    val team1Action: List<TeamActionDto>? = null,
    val team2Action: List<TeamActionDto>? = null
)