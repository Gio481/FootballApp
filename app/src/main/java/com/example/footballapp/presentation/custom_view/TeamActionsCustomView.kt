package com.example.footballapp.presentation.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import com.example.footballapp.R
import com.example.footballapp.databinding.TeamActionsCustomViewBinding
import com.example.footballapp.presentation.ui.matches.types.GoalType
import com.example.footballapp.presentation.ui.matches.types.MatchActionsType
import com.example.footballapp.presentation.ui.matches.types.MatchTeamType
import com.example.footballapp.util.extensions.isVisible
import com.example.footballapp.util.getActionType

class TeamActionsCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttrs: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttrs) {

    private val binding =
        TeamActionsCustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    var actionType: MatchActionsType? = null
        set(value) {
            value?.let { determineMatchActions(it) }
            field = value
        }

    var goalType: GoalType? = null
        set(value) {
            value?.let { determineGoalType(it) }
            field = value
        }

    var teamType: MatchTeamType? = null
        set(value) {
            value?.let { determineTeamType(it) }
            field = value
        }

    var action: String? = null
        set(value) {
            binding.actionTextView.text = value
            field = value
        }

    var subOnPlayer: String? = null
        set(value) {
            binding.mainPlayerTextView.text = value
            field = value
        }

    var subOffPlayer: String? = null
        set(value) {
            binding.subOffPlayerTextView.text = value
            field = value
        }

    var mainActionPlayer: String? = null
        set(value) {
            binding.mainPlayerTextView.text = value
            field = value
        }

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TeamActionsCustomView,
            defStyleAttrs,
            0
        )

        actionType = getActionType(binding.teamActionsLayout) {
            MatchActionsType.values()[typedArray.getInt(R.styleable.TeamActionsCustomView_actionType, 6)]
        }

        goalType =
            GoalType.values()[typedArray.getInt(R.styleable.TeamActionsCustomView_goalType, 0)]

        teamType =
            MatchTeamType.values()[typedArray.getInt(R.styleable.TeamActionsCustomView_teamType, 0)]

        action = typedArray.getString(R.styleable.TeamActionsCustomView_action)
        subOnPlayer = typedArray.getString(R.styleable.TeamActionsCustomView_subOnPlayer)
        subOffPlayer = typedArray.getString(R.styleable.TeamActionsCustomView_subOffPlayer)
        mainActionPlayer = typedArray.getString(R.styleable.TeamActionsCustomView_mainActionPlayer)
        typedArray.recycle()
    }

    private fun determineTeamType(teamType: MatchTeamType) {
        when (teamType) {
            MatchTeamType.TEAM1 -> displayTeam(1f)
            MatchTeamType.TEAM2 -> displayTeam(-1f)
        }
    }

    private fun displayTeam(float: Float) {
        with(binding) {
            teamActionsLayout.scaleX = float
            mainPlayerTextView.scaleX = float
            subOffPlayerTextView.scaleX = float
            actionTextView.scaleX = float
        }
    }


    private fun determineGoalType(goalType: GoalType) {
        if (actionType == MatchActionsType.GOAL) {
            when (goalType) {
                GoalType.GOAL -> configureActionView(FOOTBALL_BALL, GREEN_COLOR)
                GoalType.OWN_GOAL -> configureActionView(FOOTBALL_BALL, RED_COLOR)
            }
        }
    }

    private fun determineMatchActions(actionsType: MatchActionsType) {
        when (actionsType) {
            MatchActionsType.GOAL -> {
                isActionSubstitution(false)
                configureActionView(FOOTBALL_BALL)
            }
            MatchActionsType.YELLOW_CARD -> {
                isActionSubstitution(false)
                configureActionView(CARD)
            }
            MatchActionsType.RED_CARD -> {
                isActionSubstitution(false)
                configureActionView(CARD, RED_COLOR)
            }
            MatchActionsType.SUBSTITUTION -> {
                isActionSubstitution(true)
            }
        }
    }

    private fun configureActionView(item: Int, color: Int? = null) {
        with(binding.actionView) {
            background = ContextCompat.getDrawable(context, item)
            color?.let { background.setTint(ContextCompat.getColor(context, it)) }
        }
    }


    private fun isActionSubstitution(visible: Boolean) {
        with(binding) {
            actionView.isVisible(!visible)
            subOffPlayerImageView.isVisible(visible)
            subOffPlayerTextView.isVisible(visible)
            subOffView.isVisible(visible)
            subOnView.isInvisible = !visible
            teamActionsLayout.isVisible(true)
        }
    }

    companion object {
        private const val FOOTBALL_BALL = R.drawable.ic_football_ball
        private const val CARD = R.drawable.ic_card
        private const val RED_COLOR = R.color.red_700
        private const val GREEN_COLOR = R.color.green_500
    }
}