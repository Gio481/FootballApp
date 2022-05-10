package com.example.footballapp.presentation.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.footballapp.R
import com.example.footballapp.databinding.MatchInfoCutomViewBinding

class MatchInfoCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: MatchInfoCutomViewBinding by lazy {
        MatchInfoCutomViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var matchDate: String? = null
        set(value) {
            binding.matchDateTextView.text = value
            field = value
        }

    var matchStadium: String? = null
        set(value) {
            binding.matchStadiumTextView.text = value
            field = value
        }

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MatchInfoCustomView,
            defStyleAttr,
            0
        )

        matchDate = typedArray.getString(R.styleable.MatchInfoCustomView_matchDate)
        matchStadium = typedArray.getString(R.styleable.MatchInfoCustomView_matchStadium)
        typedArray.recycle()
    }
}