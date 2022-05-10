package com.example.footballapp.domain.model

data class TeamActionDomain(
    val action: ActionDomain,
    val actionType: Int,
    val teamType: Int,
)