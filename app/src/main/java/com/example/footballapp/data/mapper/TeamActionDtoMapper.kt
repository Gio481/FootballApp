package com.example.footballapp.data.mapper

import com.example.footballapp.data.model.TeamActionDto
import com.example.footballapp.domain.model.TeamActionDomain
import com.example.footballapp.util.data_mapper.ModelMapper

class TeamActionDtoMapper(private val actionDtoMapper: ActionDtoMapper) :
    ModelMapper<TeamActionDto, TeamActionDomain> {

    override fun modelMapper(model: TeamActionDto): TeamActionDomain {
        return with(model) {
            TeamActionDomain(
                action = actionDtoMapper.modelMapper(action),
                actionType = actionType,
                teamType = actionType
            )
        }
    }
}