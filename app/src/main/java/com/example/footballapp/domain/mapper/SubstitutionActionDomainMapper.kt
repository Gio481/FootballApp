package com.example.footballapp.domain.mapper

import com.example.customview.model.SubstitutionActionAttrUI
import com.example.footballapp.domain.model.SubstitutionAttrsDomain
import com.example.footballapp.util.data_mapper.ModelMapper

class SubstitutionActionDomainMapper : ModelMapper<SubstitutionAttrsDomain, SubstitutionActionAttrUI> {

    override fun modelMapper(model: SubstitutionAttrsDomain): SubstitutionActionAttrUI {
        return SubstitutionActionAttrUI(
            subOnIcon = model.subOnIcon,
            subOffIcon = model.subOffIcon,
            actionText = model.actionText
        )
    }
}