package com.example.footballapp.presentation.ui.matches

import com.example.footballapp.databinding.FragmentMatchesBinding
import com.example.footballapp.presentation.base.BaseFragment
import com.example.footballapp.presentation.ui.matches.viewmodel.MatchesViewModel
import com.example.footballapp.util.BindingInflater
import kotlin.reflect.KClass

class MatchesFragment : BaseFragment<FragmentMatchesBinding, MatchesViewModel>() {

    override val inflater: BindingInflater<FragmentMatchesBinding>
        get() = FragmentMatchesBinding::inflate

    override fun getViewModelClass(): KClass<MatchesViewModel> = MatchesViewModel::class

    override fun onBindViewModel(viewModel: MatchesViewModel) {}
}