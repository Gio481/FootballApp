package com.example.footballapp.domain.mapper

import com.example.customview.model.MatchAttributesUI
import com.example.footballapp.domain.model.MatchAttributesDomain
import com.example.footballapp.util.data_mapper.ModelMapper

class CustomAttrsDomainMapper(
    private val regularAction: RegularActionAttrsDomainMapper,
    private val substitutionAction: SubstitutionActionDomainMapper,
) : ModelMapper<MatchAttributesDomain, MatchAttributesUI> {

    override fun modelMapper(model: MatchAttributesDomain): MatchAttributesUI {
        return MatchAttributesUI(
            goal = model.goal?.let { regularAction.modelMapper(it) },
            ownGoal = model.ownGoal?.let { regularAction.modelMapper(it) },
            yellowCard = model.yellowCard?.let { regularAction.modelMapper(it) },
            redCard = model.redCard?.let { regularAction.modelMapper(it) },
            substitution = model.substitution?.let { substitutionAction.modelMapper(it) }
        )
    }
}