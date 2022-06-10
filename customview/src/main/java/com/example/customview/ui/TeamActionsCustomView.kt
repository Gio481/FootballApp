package com.example.customview.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import com.example.customview.R
import com.example.customview.databinding.TeamActionsCustomViewBinding
import com.example.customview.extension.*
import com.example.customview.types.GoalType
import com.example.customview.types.MatchActionsType
import com.example.customview.types.MatchTeamType
import com.example.customview.util.getActionType

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

    var actionText: String? = null
        set(value) {
            binding.actionTextView.text = value
            field = value
        }

    var actionIcon: Int? = null
        set(value) {
            value?.let { binding.actionView.setBackground(context, it) }
            field = value
        }

    var subOnIcon: Int? = null
        set(value) {
            value?.let { binding.subOnView.setBackground(context, it) }
            field = value
        }

    var subOffIcon: Int? = null
        set(value) {
            value?.let { binding.subOffView.setBackground(context, it) }
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
        actionText = typedArray.getString(R.styleable.TeamActionsCustomView_actionText)
        actionIcon = typedArray.getResourceId(R.styleable.TeamActionsCustomView_actionIcon, R.drawable.ic_card)
        subOnIcon = typedArray.getResourceId(R.styleable.TeamActionsCustomView_subOnIcon, R.drawable.ic_substitution)
        subOffIcon = typedArray.getResourceId(R.styleable.TeamActionsCustomView_subOffIcon, R.drawable.ic_substitution)
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
                GoalType.GOAL -> configureActionView(goalType.icon, GREEN_COLOR)
                GoalType.OWN_GOAL -> configureActionView(goalType.icon, RED_COLOR)
            }
        }
    }

    private fun determineMatchActions(actionsType: MatchActionsType) {
        when (actionsType) {
            MatchActionsType.GOAL -> {
                isActionSubstitution(false)
                configureActionView(actionsType.icon)
            }
            MatchActionsType.YELLOW_CARD -> {
                isActionSubstitution(false)
                configureActionView(actionsType.icon)
            }
            MatchActionsType.RED_CARD -> {
                isActionSubstitution(false)
                configureActionView(actionsType.icon, RED_COLOR)
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

    fun setMathActionText(matchActionsType: Int, actionTime: String, userActionText: Int? = null) {
        with(binding.actionTextView) {
            when (matchActionsType) {
                MatchActionsType.YELLOW_CARD.value -> {
                    text = (userActionText ?: TRIPPING_TEXT).getString(actionTime, context)
                }
                MatchActionsType.RED_CARD.value -> {
                    text = (userActionText ?: TRIPPING_TEXT).getString(actionTime, context)
                }
                MatchActionsType.SUBSTITUTION.value -> {
                    text = (userActionText ?: SUBSTITUTION_TEXT).getString(actionTime, context)
                }
            }
        }
    }

    fun setGoalActionText(goalType: Int, actionTime: String, userActionText: Int? = null) {
        with(binding.actionTextView) {
            when (goalType) {
                GoalType.GOAL.value -> {
                    text = (userActionText ?: GOAL_TEXT).getString(actionTime, context)
                }
                GoalType.OWN_GOAL.value -> {
                    text = (userActionText ?: OWN_GOAL_TEXT).getString(actionTime, context)
                    setColor(RED_COLOR)
                }
            }
        }
    }

    fun removeRoundView() = binding.roundDecoratorViewOnActions.isVisible(false)

    companion object {
        private val RED_COLOR = R.color.red_700
        private val GREEN_COLOR = R.color.green_500
        private val TRIPPING_TEXT = R.string.tripping_text
        private val SUBSTITUTION_TEXT = R.string.substitution_text
        private val GOAL_TEXT = R.string.goal_text
        private val OWN_GOAL_TEXT = R.string.own_goal_text
    }
}