package com.example.footballapp.presentation.match.adapter.helper

import com.example.customview.model.MatchAttributesUI
import com.example.customview.model.SummaryUI
import com.example.customview.ui.HalfTimeCustomView
import com.example.footballapp.domain.model.SummaryDomain

interface AdapterHelper {
    fun determineHalfTime(view: HalfTimeCustomView, summaryDomain: SummaryDomain)
    fun domainMapper(summary: SummaryDomain): SummaryUI
    fun setHalfTimeScore(firstHalfScore:String, secondHalfScore:String)
    fun matchAttributesMapper():MatchAttributesUI
}