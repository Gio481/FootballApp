package com.example.footballapp.presentation.match.adapter.helper

import com.example.customview.model.TeamActionUI
import com.example.customview.ui.HalfTimeCustomView
import com.example.footballapp.domain.model.SummaryDomain
import com.example.footballapp.domain.model.TeamActionDomain

interface AdapterHelper {
    fun determineHalfTime(view: HalfTimeCustomView, summaryDomain: SummaryDomain,firstHalf:String, secondHalf:String)
    fun domainMapper(list: List<TeamActionDomain>): List<TeamActionUI>
}