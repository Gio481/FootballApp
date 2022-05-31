package com.example.footballapp.domain.usecase.get_score

import com.example.customview.types.GoalType
import com.example.customview.types.MatchTeamType
import com.example.footballapp.domain.model.MatchDomain
import com.example.footballapp.domain.model.TeamActionDomain

class GetHalfTimeScoreUseCaseImpl : GetHalfTimeScoreUseCase {

    private var firstTeamScore = 0
    private var secondTeamScore = 0

    override fun getHalfTimeScore(halfTime: IntRange, match: MatchDomain?): String {
        resetTeamScores()
        match?.summary?.map {
            it.team1Action?.map { action -> countGoals(action, it.actionTime, halfTime) }
            it.team2Action?.map { action -> countGoals(action, it.actionTime, halfTime) }
        }
        return "$firstTeamScore : $secondTeamScore"
    }

    private fun countGoals(team: TeamActionDomain, actionTime: String, time: IntRange) {
        val secondTeam = team.teamType == MatchTeamType.TEAM2.value
        val firstTeam = team.teamType == MatchTeamType.TEAM1.value
        val ownGoal = team.action.goalType == GoalType.OWN_GOAL.value
        val goal = team.action.goalType == GoalType.GOAL.value

        when {
            actionTime.toInt() in time && secondTeam && goal -> {
                secondTeamScore++
            }
            actionTime.toInt() in time && secondTeam && ownGoal -> {
                firstTeamScore++
            }
            actionTime.toInt() in time && firstTeam && goal -> {
                firstTeamScore++
            }
            actionTime.toInt() in time && firstTeam && ownGoal -> {
                secondTeamScore++
            }
        }
    }

    private fun resetTeamScores() {
        firstTeamScore = 0
        secondTeamScore = 0
    }
}