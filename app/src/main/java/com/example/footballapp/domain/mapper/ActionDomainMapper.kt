package com.example.footballapp.domain.mapper

import com.example.customview.model.ActionUI
import com.example.footballapp.domain.model.ActionDomain
import com.example.footballapp.util.data_mapper.ModelMapper

class ActionDomainMapper(private val playerMapper: PlayerDomainMapper) : ModelMapper<ActionDomain, ActionUI> {

    override fun modelMapper(model: ActionDomain): ActionUI {
        return ActionUI(
            goalType = model.goalType,
            player = playerMapper.modelMapper(model.player),
            player1 = playerMapper.modelMapper(model.player1),
            player2 = playerMapper.modelMapper(model.player2)
        )
    }
}