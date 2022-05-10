package com.example.footballapp.presentation.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.footballapp.databinding.MatchOverviewCustomViewBinding

class MatchOverviewCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: MatchOverviewCustomViewBinding =
        MatchOverviewCustomViewBinding.inflate(LayoutInflater.from(context), this, true)
}