package com.example.customview.model

data class MatchAttributesUI(
    val goal: RegularActionAttrUI? = null,
    val ownGoal: RegularActionAttrUI? = null,
    val yellowCard: RegularActionAttrUI? = null,
    val redCard: RegularActionAttrUI? = null,
    val substitution: SubstitutionActionAttrUI? = null,
)