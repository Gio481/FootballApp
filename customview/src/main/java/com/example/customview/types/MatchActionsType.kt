package com.example.customview.types

import com.example.customview.R

enum class MatchActionsType(val value: Int, val icon: Int) {
    GOAL(1, R.drawable.ic_football_ball),
    YELLOW_CARD(2, R.drawable.ic_card),
    RED_CARD(3, R.drawable.ic_card),
    SUBSTITUTION(4, R.drawable.ic_substitution)
}