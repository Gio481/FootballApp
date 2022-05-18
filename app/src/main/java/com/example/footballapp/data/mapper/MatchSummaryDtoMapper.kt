package com.example.footballapp.data.mapper

import com.example.footballapp.data.model.MatchSummaryDto
import com.example.footballapp.domain.model.SummaryDomain
import com.example.footballapp.util.data_mapper.ModelMapperIntoList

class MatchSummaryDtoMapper(private val teamActionDtoMapper: TeamActionDtoMapper) :
    ModelMapperIntoList<MatchSummaryDto, SummaryDomain> {

    override fun modelMapperIntoList(model: MatchSummaryDto): List<SummaryDomain> {
        return model.summaries.map {
            with(it) {
                SummaryDomain(
                    actionTime = actionTime,
                    team1Action = team1Action?.let { action-> teamActionDtoMapper.modelListMapper(action) },
                    team2Action = team2Action?.let { action-> teamActionDtoMapper.modelListMapper(action) },
                )
            }
        }
    }
}