package com.example.footballapp.presentation.match.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import com.example.footballapp.R
import com.example.footballapp.databinding.TeamActionsCustomViewBinding
import com.example.footballapp.presentation.match.types.GoalType
import com.example.footballapp.presentation.match.types.MatchActionsType
import com.example.footballapp.presentation.match.types.MatchTeamType
import com.example.footballapp.util.extensions.*
import com.example.footballapp.util.getActionType

class TeamActionsCustomView(
    context: Context,
    attrs: AttributeSet,
    defStyleAttrs: Int = 0,
) : ConstraintLayout(context) {

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

        actionType = getActionType(binding.root) {
            MatchActionsType.values()[typedArray.getInt(R.styleable.TeamActionsCustomView_actionType,
                -1)]
        }

        goalType =
            GoalType.values()[typedArray.getInt(R.styleable.TeamActionsCustomView_goalType, 0)]

        teamType =
            MatchTeamType.values()[typedArray.getInt(R.styleable.TeamActionsCustomView_teamType, 0)]

        subOnPlayer = typedArray.getString(R.styleable.TeamActionsCustomView_subOnPlayer)
        subOffPlayer = typedArray.getString(R.styleable.TeamActionsCustomView_subOffPlayer)
        mainActionPlayer = typedArray.getString(R.styleable.TeamActionsCustomView_mainActionPlayer)
        typedArray.recycle()
    }

    private fun determineTeamType(teamType: MatchTeamType) {
        when (teamType) {
            MatchTeamType.TEAM1 -> binding.root.layoutDirection = LAYOUT_DIRECTION_LTR
            MatchTeamType.TEAM2 -> binding.root.layoutDirection = LAYOUT_DIRECTION_RTL
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
            setBackground(context, item)
            color?.let { setBackgroundTint(context, it) }
        }
    }


    private fun isActionSubstitution(visible: Boolean) {
        with(binding) {
            actionView.isInvisible = visible
            subOffPlayerImageView.isVisible(visible)
            subOffPlayerTextView.isVisible(visible)
            subOffView.isVisible(visible)
            subOnView.isInvisible = !visible
            root.isVisible(true)
        }
    }

    fun setNonSubstitutionPlayerImage(mainPlayerUrl: String?) {
        mainPlayerUrl?.let { binding.mainPlayerImageView.setImage(it) }
    }

    fun setSubstitutionPlayersImage(subOnPlayer: String?, subOffPlayer: String?) {
        subOnPlayer?.let { binding.mainPlayerImageView.setImage(it) }
        subOffPlayer?.let { binding.subOffPlayerImageView.setImage(it) }
    }

    fun setMathActionText(matchActionsType: Int, actionTime: String) {
        with(binding.actionTextView) {
            when (matchActionsType) {
                MatchActionsType.YELLOW_CARD.value -> text = TRIPPING_TEXT.getString(actionTime, context)
                MatchActionsType.RED_CARD.value -> text = TRIPPING_TEXT.getString(actionTime, context)
                MatchActionsType.SUBSTITUTION.value -> text = SUBSTITUTION_TEXT.getString(actionTime, context)
            }
        }
    }

    fun setGoalActionText(goalType: Int, actionTime: String) {
        with(binding.actionTextView) {
            when (goalType) {
                GoalType.GOAL.value -> text = GOAL_TEXT.getString(actionTime, context)
                GoalType.OWN_GOAL.value -> {
                    text = OWN_GOAL_TEXT.getString(actionTime, context)
                    setColor(RED_COLOR)
                }
            }
        }
    }

    fun removeRoundView() {
        binding.roundDecoratorViewOnActions.isVisible(false)
    }

    companion object {
        private const val FOOTBALL_BALL = R.drawable.ic_football_ball
        private const val CARD = R.drawable.ic_card
        private const val RED_COLOR = R.color.red_700
        private const val GREEN_COLOR = R.color.green_500
        private const val TRIPPING_TEXT = R.string.tripping_text
        private const val SUBSTITUTION_TEXT = R.string.substitution_text
        private const val GOAL_TEXT = R.string.goal_text
        private const val OWN_GOAL_TEXT = R.string.own_goal_text
    }
}