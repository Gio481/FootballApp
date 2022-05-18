package com.example.footballapp.data.mapper

import com.example.footballapp.data.model.ActionDto
import com.example.footballapp.domain.model.ActionDomain
import com.example.footballapp.util.data_mapper.ModelMapper

class ActionDtoMapper(private val playerDtoMapper: PlayerDtoMapper) :
    ModelMapper<ActionDto, ActionDomain> {

    override fun modelMapper(model: ActionDto): ActionDomain {
        return with(model) {
            with(playerDtoMapper) {
                ActionDomain(
                    goalType = goalType,
                    player = modelMapper(player),
                    player1 = modelMapper(player1),
                    player2 = modelMapper(player2)
                )
            }
        }
    }
}