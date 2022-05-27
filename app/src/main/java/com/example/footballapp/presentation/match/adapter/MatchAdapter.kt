package com.example.footballapp.presentation.match.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapp.databinding.MatchActionLayoutBinding
import com.example.footballapp.domain.model.SummaryDomain
import com.example.footballapp.presentation.match.adapter.helper.AdapterHelper
import com.example.footballapp.util.ItemsDiffUtil

class MatchAdapter(private val helper: AdapterHelper) : ListAdapter<SummaryDomain, MatchAdapter.ViewHolder>(ItemsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MatchActionLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position), helper)
    }

    class ViewHolder(private val binding: MatchActionLayoutBinding, ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(summaryDomain: SummaryDomain, helper: AdapterHelper) {
            with(binding) {
                with(summaryDomain) {
                    helper.determineHalfTime(halfTimeCustomView, summaryDomain)
                    team1Action?.let { team1ActionsContainerCustomView.getFootballTeamInfo(it, actionTime) }
                    team2Action?.let { team2ActionsContainerCustomView.getFootballTeamInfo(it, actionTime) }
                }
            }
        }
    }
}