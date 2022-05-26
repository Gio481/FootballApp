package com.example.footballapp.presentation.match.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.domain.model.MatchDomain
import com.example.footballapp.util.KoinComponentInstances
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchesViewModel : ViewModel() {
    private val koinComponents by lazy { KoinComponentInstances() }

    private val _footballMatchLiveData: MutableLiveData<MatchDomain> = MutableLiveData()
    val footballMatchLiveData: LiveData<MatchDomain> = _footballMatchLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun getFootballMatch() {
        viewModelScope.launch(Dispatchers.IO) {
            _footballMatchLiveData.postValue(koinComponents.matchUseCase.getFootballMatch {
                _errorLiveData.postValue(it)
            })
        }
    }

    suspend fun firstHalfScore(): String {
        return koinComponents.matchUseCase.getFirstHalfScore {
            _errorLiveData.postValue(it)
        }
    }

    suspend fun secondHalfScore(): String {
        return koinComponents.matchUseCase.getSecondHalfScore {
            _errorLiveData.postValue(it)
        }
    }
}