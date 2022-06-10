package com.example.footballapp.domain.mapper

import com.example.customview.model.SummaryUI
import com.example.footballapp.domain.model.SummaryDomain
import com.example.footballapp.util.data_mapper.ModelMapper

class SummaryDomainMapper(private val teamActionDomainMapper: TeamActionDomainMapper):ModelMapper<SummaryDomain, SummaryUI> {
    override fun modelMapper(model: SummaryDomain): SummaryUI {
        return SummaryUI(
            actionTime = model.actionTime,
            team1Action = model.team1Action?.let { teamActionDomainMapper.modelListMapper(it) },
            team2Action = model.team2Action?.let { teamActionDomainMapper.modelListMapper(it) }
        )
    }
}