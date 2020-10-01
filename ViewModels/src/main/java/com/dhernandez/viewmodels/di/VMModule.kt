package com.dhernandez.viewmodels.di

import com.dhernandez.viewmodels.HomeViewModel
import com.dhernandez.viewmodels.FragmentViewModel
import com.dhernandez.viewmodels.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val VMModule = module {

    //ViewModels definitions
    viewModel { SplashViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { FragmentViewModel(get(named("detail"))) }

}