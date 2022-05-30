package com.example.footballapp.presentation.match.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballapp.databinding.FragmentMatchesBinding
import com.example.footballapp.domain.mapper.TeamActionDomainMapper
import com.example.footballapp.domain.model.TeamDomain
import com.example.footballapp.presentation.base.ui.BaseFragment
import com.example.footballapp.presentation.match.adapter.MatchAdapter
import com.example.footballapp.presentation.match.adapter.helper.AdapterHelper
import com.example.footballapp.presentation.match.adapter.helper.AdapterHelperImpl
import com.example.footballapp.presentation.match.viewmodel.MatchesViewModel
import com.example.footballapp.util.BindingInflater
import com.example.footballapp.util.extensions.*
import org.koin.android.ext.android.inject

class MatchesFragment : BaseFragment<FragmentMatchesBinding, MatchesViewModel>() {

    override val inflater: BindingInflater<FragmentMatchesBinding>
        get() = FragmentMatchesBinding::inflate

    override fun getViewModelClass(): Class<MatchesViewModel> = MatchesViewModel::class.java

    private val mapper by inject<TeamActionDomainMapper>()
    private val helper: AdapterHelper by lazy { AdapterHelperImpl(mapper) }
    private val matchAdapter by lazy { MatchAdapter(helper) }

    override fun onBindViewModel(viewModel: MatchesViewModel) {
        viewModel.getFootballMatch()
        observeMatchLiveData(viewModel)
        setUpRecyclerView()
        observeErrorLiveData(viewModel)
    }


    private fun observeMatchLiveData(viewModel: MatchesViewModel) {
        observer(viewModel.footballMatchLiveData) { matchDomain ->
            with(matchDomain) {
                configureBallPossession(team1, team2)
                configureMatchInfoCustomView(matchDate, stadiumAddress)
                configureClubInfoCustomView(team1, team2, matchTime)
                matchAdapter.submitList(summary)
                matchAdapter.setHalfTimeScore(viewModel.getHalfTimeScore(0..45, matchDomain),
                    viewModel.getHalfTimeScore(45..90, matchDomain))
            }
        }
    }

    private fun configureClubInfoCustomView(team1: TeamDomain, team2: TeamDomain, time: Double) {
        with(binding.clubsInfoCustomView) {
            homeTeamName = team1.teamName
            awayTeamName = team2.teamName
            setHomeTeamLogo(team1.teamImage)
            setAwayTeamLogo(team2.teamImage)
            binding.clubsInfoCustomView.time = time.getTime()
            score = team1.score.getScore(team2.score)
        }
    }

    private fun configureMatchInfoCustomView(date: Long, stadium: String) {
        with(binding.matchInfoCustomView) {
            matchDate = date.formatDate()
            matchStadium = stadium
        }
    }

    private fun configureBallPossession(team1: TeamDomain, team2: TeamDomain) {
        with(binding.clubsBallPossessionCustomView) {
            with(team1) {
                homeTeamPossession = ballPosition.getPossession()
                possession = ballPosition
            }
            awayTeamPossession = team2.ballPosition.getPossession()
        }
    }

    private fun observeErrorLiveData(viewModel: MatchesViewModel) {
        observer(viewModel.errorLiveData) {
            showToast(it)
        }
    }

    private fun setUpRecyclerView() {
        with(binding.matchActionsRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = matchAdapter
        }
    }
}