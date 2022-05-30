package com.example.footballapp.presentation.match.adapter.helper

import com.example.customview.extension.isVisible
import com.example.customview.model.TeamActionUI
import com.example.customview.types.HalfTimeType
import com.example.customview.ui.HalfTimeCustomView
import com.example.footballapp.domain.mapper.TeamActionDomainMapper
import com.example.footballapp.domain.model.SummaryDomain
import com.example.footballapp.domain.model.TeamActionDomain

class AdapterHelperImpl(
    private val mapper: TeamActionDomainMapper,
) : AdapterHelper {

    private var isFirstHalfChecked = false
    private var isSecondHalfChecked = false

    override fun determineHalfTime(
        view: HalfTimeCustomView,
        summaryDomain: SummaryDomain,
        firstHalf: String,
        secondHalf: String,
    ) {
        if (summaryDomain.actionTime.toInt() < FIRST_HALF_TIME_MINUTES && !isFirstHalfChecked) {
            isFirstHalfChecked = true
            showHalfTimeView(view, HalfTimeType.FirstHalf, firstHalf)
        } else if (summaryDomain.actionTime.toInt() > FIRST_HALF_TIME_MINUTES && !isSecondHalfChecked) {
            isSecondHalfChecked = true
            showHalfTimeView(view, HalfTimeType.SecondHalf, secondHalf)
        } else {

            view.isVisible(false)
        }
    }

    private fun showHalfTimeView(
        view: HalfTimeCustomView,
        halfTimeType: HalfTimeType,
        halfScore: String,
    ) {
        with(view) {
            halfTime = halfTimeType
            score = halfScore
            isVisible(true)
        }
    }

    override fun domainMapper(list: List<TeamActionDomain>): List<TeamActionUI> {
        return mapper.modelListMapper(list)
    }

    companion object {
        private const val FIRST_HALF_TIME_MINUTES = 45
    }
}