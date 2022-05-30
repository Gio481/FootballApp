package com.example.customview.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.customview.databinding.TeamActionsContainerCustomViewBinding
import com.example.customview.extension.getName
import com.example.customview.model.ActionUI
import com.example.customview.model.TeamActionUI
import com.example.customview.types.GoalType
import com.example.customview.types.MatchActionsType
import com.example.customview.types.MatchTeamType

class TeamActionsContainerCustomView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet,
    defStyleAttrs: Int = 0,
) : LinearLayout(context, attrs, defStyleAttrs) {

    private val binding =
        TeamActionsContainerCustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun getFootballTeamInfo(team: List<TeamActionUI>, actionTime: String) {
        team.map { determineTeamType(it, actionTime, team.size) }
    }

    private fun determineTeamType(
        teamAction: TeamActionUI,
        actionTime: String,
        actionsListSize: Int,
    ) {
        val teamActionsView = TeamActionsCustomView(context, attrs)

        with(binding) {
            when (teamAction.teamType) {
                MatchTeamType.TEAM1.value -> {
                    configureMultipleActions(teamActionsView, actionsListSize)
                    teamActionsView.teamType = MatchTeamType.TEAM1
                    determineTeamActions(teamActionsView, teamAction, actionTime)
                    team1.addView(teamActionsView)
                }
                MatchTeamType.TEAM2.value -> {
                    configureMultipleActions(teamActionsView, actionsListSize)
                    teamActionsView.teamType = MatchTeamType.TEAM2
                    determineTeamActions(teamActionsView, teamAction, actionTime)
                    team2.addView(teamActionsView)
                }
            }
        }
    }

    private fun configureMultipleActions(view: TeamActionsCustomView, actionListSize: Int) {
        if (actionListSize > ONE_ITEM) view.removeRoundView()
    }

    private fun determineTeamActions(
        view: TeamActionsCustomView,
        action: TeamActionUI,
        actionTime: String,
    ) {
        with(view) {
            when (action.actionType) {
                MatchActionsType.RED_CARD.value -> {
                    nonSubstitutionPlayers(view, action.action)
                    actionType = MatchActionsType.RED_CARD
                    setMathActionText(action.actionType, actionTime)
                }
                MatchActionsType.YELLOW_CARD.value -> {
                    nonSubstitutionPlayers(view, action.action)
                    actionType = MatchActionsType.YELLOW_CARD
                    setMathActionText(action.actionType, actionTime)
                }
                MatchActionsType.GOAL.value -> {
                    nonSubstitutionPlayers(view, action.action)
                    actionType = MatchActionsType.GOAL
                    determineGoalType(view, action.action, actionTime)
                }
                MatchActionsType.SUBSTITUTION.value -> {
                    substitutionPlayers(view, action.action)
                    actionType = MatchActionsType.SUBSTITUTION
                    setMathActionText(action.actionType, actionTime)
                }
            }
        }
    }

    private fun determineGoalType(
        view: TeamActionsCustomView,
        action: ActionUI?,
        actionTime: String,
    ) {
        with(view) {
            when (action?.goalType) {
                GoalType.GOAL.value -> {
                    goalType = GoalType.GOAL
                    setGoalActionText(action.goalType, actionTime)
                }
                GoalType.OWN_GOAL.value -> {
                    view.goalType = GoalType.OWN_GOAL
                    setGoalActionText(action.goalType, actionTime)
                }
            }
        }
    }

    private fun nonSubstitutionPlayers(view: TeamActionsCustomView, action: ActionUI) {
        view.mainActionPlayer = action.player?.playerName?.getName()
    }

    private fun substitutionPlayers(view: TeamActionsCustomView, action: ActionUI) {
        view.subOnPlayer = action.player1?.playerName?.getName()
        view.subOffPlayer = action.player2?.playerName?.getName()
    }

    companion object {
        private const val ONE_ITEM = 1
    }
}