package com.example.footballapp.data.mapper

import com.example.footballapp.data.model.FootballMatchDto
import com.example.footballapp.domain.model.MatchDomain
import com.example.footballapp.util.data_mapper.ModelMapper

class FootballMatchDtoMapper(
    private val teamDtoMapper: TeamDtoMapper,
    private val matchSummaryDtoMapper: MatchSummaryDtoMapper,
) : ModelMapper<FootballMatchDto, MatchDomain> {

    override fun modelMapper(model: FootballMatchDto): MatchDomain {
        return with(model.match) {
            MatchDomain(
                matchDate = matchDate,
                matchTime = matchTime,
                stadiumAddress = stadiumAdress,
                team1 = teamDtoMapper.modelMapper(team1),
                team2 = teamDtoMapper.modelMapper(team2),
                summary = matchSummaryDtoMapper.modelMapperIntoList(matchSummary)
            )
        }
    }
}