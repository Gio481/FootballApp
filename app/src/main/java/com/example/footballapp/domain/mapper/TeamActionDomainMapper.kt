package com.example.footballapp.domain.mapper

import com.example.customview.model.TeamActionUI
import com.example.footballapp.domain.model.TeamActionDomain
import com.example.footballapp.util.data_mapper.ModelMapper

class TeamActionDomainMapper(private val actionMapper: ActionDomainMapper) : ModelMapper<TeamActionDomain, TeamActionUI> {

    override fun modelMapper(model: TeamActionDomain): TeamActionUI {
        return TeamActionUI(
            action = actionMapper.modelMapper(model.action),
            actionType = model.actionType,
            teamType = model.teamType)
    }
}