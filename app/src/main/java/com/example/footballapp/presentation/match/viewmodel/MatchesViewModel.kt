package com.example.footballapp.presentation.match.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customview.types.GoalType
import com.example.customview.types.MatchTeamType
import com.example.footballapp.domain.model.MatchDomain
import com.example.footballapp.domain.model.TeamActionDomain
import com.example.footballapp.util.KoinComponentInstances
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchesViewModel : ViewModel() {
    private val koinComponents by lazy { KoinComponentInstances() }

    private val _footballMatchLiveData: MutableLiveData<MatchDomain> = MutableLiveData()
    val footballMatchLiveData: LiveData<MatchDomain> = _footballMatchLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    private var firstTeamScore = 0
    private var secondTeamScore = 0

    fun getFootballMatch() {
        viewModelScope.launch(Dispatchers.IO) {
            _footballMatchLiveData.postValue(koinComponents.matchUseCase.getFootballMatch {
                _errorLiveData.postValue(it)
            })
        }
    }

    fun getHalfTimeScore(
        halfTime: IntRange,
        match: MatchDomain?,
    ): String {
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