package com.example.footballapp.domain.mapper

import com.example.customview.model.PlayerUI
import com.example.footballapp.domain.model.PlayerDomain
import com.example.footballapp.util.data_mapper.ModelMapper

class PlayerDomainMapper : ModelMapper<PlayerDomain?, PlayerUI> {

    override fun modelMapper(model: PlayerDomain?): PlayerUI {
        return PlayerUI(
            playerImage = model?.playerImage,
            playerName = model?.playerName
        )
    }
}