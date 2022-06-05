package com.example.remote.di

import com.example.core.Constants.BASE_URL
import com.example.domain.repository.CockTailsRepository
import com.example.remote.api.CockTailsApi
import com.example.remote.repository.CockTailsRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteDataModule = module {
    single <CockTailsRepository> { CockTailsRepositoryImpl(get(), get()) }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CockTailsApi::class.java)
    }
}
