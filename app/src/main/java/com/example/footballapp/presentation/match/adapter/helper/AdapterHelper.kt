package com.example.footballapp.presentation.match.adapter.helper

import com.example.footballapp.domain.model.SummaryDomain
import com.example.footballapp.presentation.match.custom_view.HalfTimeCustomView

interface AdapterHelper {
    fun determineHalfTime(view: HalfTimeCustomView, summaryDomain: SummaryDomain)
}