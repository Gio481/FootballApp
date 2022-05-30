package com.example.footballapp.presentation.match.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.footballapp.R
import com.example.footballapp.databinding.ClubsInfoCustomViewBinding
import com.example.footballapp.util.extensions.setImage

class ClubsInfoCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ClubsInfoCustomViewBinding =
        ClubsInfoCustomViewBinding.inflate(LayoutInflater.from(context), this, true)


    var homeTeamName: String? = null
        set(value) {
            binding.homeClubNameTextView.text = value
            field = value
        }

    var awayTeamName: String? = null
        set(value) {
            binding.awayClubNameTextView.text = value
            field = value
        }

    var score: String? = null
        set(value) {
            binding.scoreTextView.text = value
            field = value
        }

    var time: String? = null
        set(value) {
            binding.matchTimeTextView.text = value
            field = value
        }

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ClubsInfoCustomView,
            defStyleAttr,
            0
        )
        homeTeamName = typedArray.getString(R.styleable.ClubsInfoCustomView_homeTeamName)
        awayTeamName = typedArray.getString(R.styleable.ClubsInfoCustomView_awayTeamName)
        score = typedArray.getString(R.styleable.ClubsInfoCustomView_score)
        time = typedArray.getString(R.styleable.ClubsInfoCustomView_time)
        typedArray.recycle()
    }

    fun setHomeTeamLogo(url: String) {
        binding.homeClubLogoImageView.setImage(url)
    }

    fun setAwayTeamLogo(url: String) {
        binding.awayClubLogoImageView.setImage(url)
    }
}