package com.example.footballapp.presentation.match.ui

import com.example.footballapp.databinding.FragmentMatchesBinding
import com.example.footballapp.presentation.base.ui.BaseFragment
import com.example.footballapp.presentation.match.viewmodel.MatchesViewModel
import com.example.footballapp.util.BindingInflater

class MatchesFragment : BaseFragment<FragmentMatchesBinding, MatchesViewModel>() {

    override val inflater: BindingInflater<FragmentMatchesBinding>
        get() = FragmentMatchesBinding::inflate

    override fun getViewModelClass(): Class<MatchesViewModel> = MatchesViewModel::class.java

    override fun onBindViewModel(viewModel: MatchesViewModel) {}
}