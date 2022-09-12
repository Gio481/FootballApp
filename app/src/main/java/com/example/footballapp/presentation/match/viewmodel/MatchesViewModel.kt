package com.example.footballapp.presentation.match.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.domain.model.MatchDomain
import com.example.footballapp.domain.usecase.get_match.GetFootballMatchUseCase
import com.example.footballapp.domain.usecase.get_score.GetHalfTimeScoreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchesViewModel(
    private val getFootballMatchUseCase: GetFootballMatchUseCase,
    private val getHalfTimeScoreUseCase: GetHalfTimeScoreUseCase,
) : ViewModel() {

    private val _footballMatchLiveData: MutableLiveData<MatchDomain> = MutableLiveData()
    val footballMatchLiveData: LiveData<MatchDomain> = _footballMatchLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun getFootballMatch() {
        viewModelScope.launch(Dispatchers.IO) {
            _footballMatchLiveData.postValue(getFootballMatchUseCase.getFootballMatch {
                _errorLiveData.postValue(it)
            })
        }
    }

    fun getHalfTimeScore(halfTime: IntRange, matchDomain: MatchDomain?): String {
        return getHalfTimeScoreUseCase.getHalfTimeScore(halfTime, matchDomain)
    }
}