package com.example.footballapp.domain.model

data class ActionDomain(
    val goalType: Int?,
    val player: PlayerDomain?,
    val player1: PlayerDomain?,
    val player2: PlayerDomain?,
)