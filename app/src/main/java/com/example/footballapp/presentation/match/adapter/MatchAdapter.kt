package com.example.footballapp.presentation.match.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapp.databinding.MatchActionLayoutBinding
import com.example.footballapp.domain.model.SummaryDomain
import com.example.footballapp.presentation.match.adapter.helper.AdapterHelper
import com.example.footballapp.util.ItemsDiffUtil

class MatchAdapter(private val helper: AdapterHelper) :
    ListAdapter<SummaryDomain, MatchAdapter.ViewHolder>(ItemsDiffUtil()) {

    private lateinit var firsHalfSore: String
    private lateinit var secondHalfScore: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MatchActionLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position), helper, firsHalfSore, secondHalfScore)
    }


    class ViewHolder(private val binding: MatchActionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(summaryDomain: SummaryDomain, helper: AdapterHelper, firsHalf: String, secondHalf: String) {
            with(binding) {
                with(summaryDomain) {
                    helper.determineHalfTime(halfScoreCustomView, summaryDomain, firsHalf, secondHalf)
                    team1Action?.let { team1ActionsContainerCustomView.getFootballTeamInfo(helper.domainMapper(it), actionTime) }
                    team2Action?.let { team2ActionsContainerCustomView.getFootballTeamInfo(helper.domainMapper(it), actionTime) }
                }
            }
        }
    }

    fun setHalfTimeScore(firsHalf: String, secondHalf: String) {
        this.firsHalfSore = firsHalf
        this.secondHalfScore = secondHalf
    }
}