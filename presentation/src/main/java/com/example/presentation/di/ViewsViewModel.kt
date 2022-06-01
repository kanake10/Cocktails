package com.example.presentation.di

import com.example.presentation.viemodel.CockTailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CockTailViewModel(get()) }
}