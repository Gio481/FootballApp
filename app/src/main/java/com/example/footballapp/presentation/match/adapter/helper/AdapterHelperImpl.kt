package com.example.footballapp.presentation.match.adapter.helper

import com.example.customview.extension.isVisible
import com.example.customview.model.MatchAttributesUI
import com.example.customview.model.SummaryUI
import com.example.customview.types.HalfTimeType
import com.example.customview.ui.HalfTimeCustomView
import com.example.footballapp.domain.mapper.CustomAttrsDomainMapper
import com.example.footballapp.domain.mapper.SummaryDomainMapper
import com.example.footballapp.domain.model.SummaryDomain

class AdapterHelperImpl(
    private val mapper: SummaryDomainMapper,
    private val customAttrsMapper: CustomAttrsDomainMapper,
    private val attrsCreator: MatchAttributesCreator
) : AdapterHelper {

    private var isFirstHalfChecked = false
    private var isSecondHalfChecked = false
    private var firstHalfScore: String = ""
    private var secondHalfScore: String = ""

    override fun determineHalfTime(view: HalfTimeCustomView, summaryDomain: SummaryDomain) {
        if (summaryDomain.actionTime.toInt() < FIRST_HALF_TIME_MINUTES && !isFirstHalfChecked) {
            isFirstHalfChecked = true
            showHalfTimeView(view, HalfTimeType.FirstHalf, firstHalfScore)
        } else if (summaryDomain.actionTime.toInt() > FIRST_HALF_TIME_MINUTES && !isSecondHalfChecked) {
            isSecondHalfChecked = true
            showHalfTimeView(view, HalfTimeType.SecondHalf, secondHalfScore)
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

    override fun domainMapper(summary: SummaryDomain): SummaryUI {
        return mapper.modelMapper(summary)
    }

    override fun setHalfTimeScore(firstHalfScore: String, secondHalfScore: String) {
        this.firstHalfScore = firstHalfScore
        this.secondHalfScore = secondHalfScore
    }

    override fun matchAttributesMapper(): MatchAttributesUI {
        return customAttrsMapper.modelMapper(attrsCreator.customAttrs)
    }


    companion object {
        private const val FIRST_HALF_TIME_MINUTES = 45
    }
}