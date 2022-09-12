package com.example.footballapp.di

import com.example.footballapp.presentation.match.viewmodel.MatchesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MatchesViewModel(get(), get()) }
}