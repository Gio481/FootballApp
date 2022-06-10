package com.example.footballapp.domain.mapper

import com.example.customview.model.RegularActionAttrUI
import com.example.footballapp.domain.model.RegularActionAttrsDomain
import com.example.footballapp.util.data_mapper.ModelMapper

class RegularActionAttrsDomainMapper : ModelMapper<RegularActionAttrsDomain, RegularActionAttrUI> {
    override fun modelMapper(model: RegularActionAttrsDomain): RegularActionAttrUI {
        return RegularActionAttrUI(
            actionText = model.actionText,
            icon = model.icon
        )
    }
}