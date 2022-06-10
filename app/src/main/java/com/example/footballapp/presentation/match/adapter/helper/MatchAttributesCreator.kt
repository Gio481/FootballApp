package com.example.footballapp.presentation.match.adapter.helper

import com.example.footballapp.R
import com.example.footballapp.domain.model.MatchAttributesDomain
import com.example.footballapp.domain.model.RegularActionAttrsDomain

class MatchAttributesCreator {
    private val yellowCardAttrs = RegularActionAttrsDomain(actionText = R.string.videos_screen)
    private val goal = RegularActionAttrsDomain(actionText = R.string.settings_screen)
    val customAttrs = MatchAttributesDomain(yellowCard = yellowCardAttrs, goal = goal)
}