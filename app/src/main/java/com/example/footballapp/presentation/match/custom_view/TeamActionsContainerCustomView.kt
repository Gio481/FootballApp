package com.example.footballapp.presentation.match.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.footballapp.databinding.TeamActionsContainerCustomViewBinding
import com.example.footballapp.domain.model.ActionDomain
import com.example.footballapp.domain.model.TeamActionDomain
import com.example.footballapp.presentation.match.types.GoalType
import com.example.footballapp.presentation.match.types.MatchActionsType
import com.example.footballapp.presentation.match.types.MatchTeamType
import com.example.footballapp.util.extensions.getName

class TeamActionsContainerCustomView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet,
    defStyleAttrs: Int = 0,
) : LinearLayout(context, attrs, defStyleAttrs) {

    private val binding =
        TeamActionsContainerCustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun getFootballTeamInfo(team: List<TeamActionDomain>, actionTime: String) {
        team.map { determineTeamType(it, actionTime, team.size) }
    }

    private fun determineTeamType(teamAction: TeamActionDomain, actionTime: String, actionsListSize: Int) {
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

    private fun configureMultipleActions(view:TeamActionsCustomView,actionListSize:Int){
        if (actionListSize > 1) view.removeRoundView()
    }

    private fun determineTeamActions(
        view: TeamActionsCustomView,
        action: TeamActionDomain,
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
        action: ActionDomain?,
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

    private fun nonSubstitutionPlayers(view: TeamActionsCustomView, action: ActionDomain) {
        view.mainActionPlayer = action.player?.playerName?.getName()
    }

    private fun substitutionPlayers(view: TeamActionsCustomView, action: ActionDomain) {
        view.subOnPlayer = action.player1?.playerName?.getName()
        view.subOffPlayer = action.player2?.playerName?.getName()
    }

}