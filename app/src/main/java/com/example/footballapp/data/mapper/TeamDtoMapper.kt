package com.example.footballapp.data.mapper

import com.example.footballapp.data.model.TeamDto
import com.example.footballapp.domain.model.TeamDomain
import com.example.footballapp.util.data_mapper.ModelMapper

class TeamDtoMapper : ModelMapper<TeamDto, TeamDomain> {

    override fun modelMapper(model: TeamDto): TeamDomain {
        return with(model) {
            TeamDomain(
                ballPosition = ballPosition,
                score = score,
                teamImage = teamImage,
                teamName = teamName
            )
        }
    }
}