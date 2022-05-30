package com.example.customview.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customview.R
import com.example.customview.databinding.ClubsBallPossessionCustomViewBinding

class ClubsBallPossessionCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ClubsBallPossessionCustomViewBinding.inflate(LayoutInflater.from(context), this, true)


    var homeTeamPossession: String? = null
        set(value) {
            binding.homeTeamBallPossessionTextView.text = value
            field = value
        }

    var awayTeamPossession: String? = null
        set(value) {
            binding.awayTeamBallPossessionTextView.text = value
            field = value
        }

    var possession: Int = 0
        set(value) {
            binding.ballPossessionProgressBar.progress = value
            field = value
        }

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ClubsBallPossessionCustomView,
            defStyleAttr,
            0
        )
        homeTeamPossession =
            typedArray.getString(R.styleable.ClubsBallPossessionCustomView_homeTeamPossession)
        awayTeamPossession =
            typedArray.getString(R.styleable.ClubsBallPossessionCustomView_awayTeamPossession)
        possession = typedArray.getInt(R.styleable.ClubsBallPossessionCustomView_possession, 0)
        typedArray.recycle()
    }

}