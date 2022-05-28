package com.example.footballapp.domain.usecase

import com.example.footballapp.domain.model.MatchDomain
import com.example.footballapp.domain.model.TeamActionDomain
import com.example.footballapp.domain.repository.FootballMatchRepository
import com.example.footballapp.presentation.match.types.GoalType
import com.example.footballapp.presentation.match.types.MatchTeamType
import com.example.footballapp.util.repositoryDataFetcher

class GetFootballMatchUseCaseImpl(private val repository: FootballMatchRepository) :
    GetFootballMatchUseCase {

    private var firstTeamScore = 0
    private var secondTeamScore = 0
    override suspend fun getFootballMatch(action: (message: String) -> Unit): MatchDomain? {
        return repositoryDataFetcher({ repository.footballMatch() }, { action(it) })
    }


    override suspend fun getFirstHalfScore(action: (message: String) -> Unit): String {
        makeTeamsScoresNull()
        getFootballMatch(action)?.summary?.map {
            it.team1Action?.map { action -> countGoals(action, it.actionTime, 0..45) }
            it.team2Action?.map { action -> countGoals(action, it.actionTime, 0..45) }
        }
        return "$firstTeamScore : $secondTeamScore"
    }

    override suspend fun getSecondHalfScore(action: (message: String) -> Unit): String {
        makeTeamsScoresNull()
        getFootballMatch(action)?.summary?.map {
            it.team1Action?.map { action -> countGoals(action, it.actionTime, 46..90) }
            it.team2Action?.map { action -> countGoals(action, it.actionTime, 46..90) }
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

    private fun makeTeamsScoresNull() {
        firstTeamScore = 0
        secondTeamScore = 0
    }
}