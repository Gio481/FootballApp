package com.example.footballapp.data.mapper

import com.example.footballapp.data.model.PlayerDto
import com.example.footballapp.domain.model.PlayerDomain
import com.example.footballapp.util.data_mapper.ModelMapper

class PlayerDtoMapper : ModelMapper<PlayerDto?, PlayerDomain> {

    override fun modelMapper(model: PlayerDto?): PlayerDomain {
        return PlayerDomain(
            playerImage = model?.playerImage,
            playerName = model?.playerName
        )
    }
}