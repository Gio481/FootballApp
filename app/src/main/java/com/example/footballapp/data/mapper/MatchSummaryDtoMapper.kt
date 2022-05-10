package com.example.footballapp.data.mapper

import com.example.footballapp.data.model.MatchSummaryDto
import com.example.footballapp.domain.model.SummaryDomain
import com.example.footballapp.util.data_mapper.ModelMapperIntoList

class MatchSummaryDtoMapper(private val teamActionDtoMapper: TeamActionDtoMapper) :
    ModelMapperIntoList<MatchSummaryDto, SummaryDomain> {

    override fun modelMapperIntoList(model: MatchSummaryDto): List<SummaryDomain> {
        return model.summaries.map {
            SummaryDomain(
                actionTime = it.actionTime,
                team1Action = teamActionDtoMapper.modelListMapper(it.team1Action),
                team2Action = teamActionDtoMapper.modelListMapper(it.team2Action),
            )
        }
    }
}