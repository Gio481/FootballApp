package com.example.footballapp.presentation.match.adapter.helper

import com.example.footballapp.domain.model.SummaryDomain
import com.example.footballapp.presentation.match.custom_view.HalfTimeCustomView
import com.example.footballapp.presentation.match.types.HalfTimeType
import com.example.footballapp.util.extensions.isVisible

class AdapterHelperImpl(private val firsHalfScore:String, private val secondHalfScore:String):AdapterHelper {

    private var isFirstHalfChecked = false
    private var isSecondHalfChecked = false

    override fun determineHalfTime(view: HalfTimeCustomView, summaryDomain: SummaryDomain) {
        if (summaryDomain.actionTime.toInt() < FIRST_HALF_TIME_MINUTES && !isFirstHalfChecked) {
            isFirstHalfChecked = true
            showHalfTimeView(view, HalfTimeType.FirstHalf, firsHalfScore)
        } else if (summaryDomain.actionTime.toInt() > FIRST_HALF_TIME_MINUTES && !isSecondHalfChecked) {
            isSecondHalfChecked = true
            showHalfTimeView(view, HalfTimeType.SecondHalf, secondHalfScore)
        } else {
            view.isVisible(false)
        }
    }

    private fun showHalfTimeView(view: HalfTimeCustomView, halfTimeType: HalfTimeType, halfTimeScore:String) {
        with(view) {
            halfTime = halfTimeType
            score = halfTimeScore
            isVisible(true)
        }
    }

    companion object {
        private const val FIRST_HALF_TIME_MINUTES = 45
    }
}