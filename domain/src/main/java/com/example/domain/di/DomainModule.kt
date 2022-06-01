package com.example.domain.di

import com.example.domain.usecases.GetCockTailsUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetCockTailsUseCase(get()) }

}