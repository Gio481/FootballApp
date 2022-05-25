package com.example.footballapp.presentation.match.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.footballapp.databinding.TeamActionsContainerCustomViewBinding

class TeamActionsContainerCustomView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet,
    defStyleAttrs: Int = 0,
) : LinearLayout(context, attrs, defStyleAttrs) {

    private val binding =
        TeamActionsContainerCustomViewBinding.inflate(LayoutInflater.from(context), this, true)
}