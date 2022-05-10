package com.example.footballapp.data.model

data class ActionDto(
    val goalType: Int,
    val player: PlayerDto?,
    val player1: PlayerDto?,
    val player2: PlayerDto?,
)