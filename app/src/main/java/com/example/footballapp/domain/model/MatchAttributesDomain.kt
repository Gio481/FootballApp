package com.example.footballapp.domain.model

data class MatchAttributesDomain(
    val goal: RegularActionAttrsDomain? = null,
    val ownGoal: RegularActionAttrsDomain? = null,
    val yellowCard: RegularActionAttrsDomain? = null,
    val redCard: RegularActionAttrsDomain? = null,
    val substitution: SubstitutionAttrsDomain? = null,
)