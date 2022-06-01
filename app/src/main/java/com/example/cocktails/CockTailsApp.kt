package com.example.cocktails

import android.app.Application
import com.example.cache.db.localDataModule
import com.example.domain.di.domainModule
import com.example.presentation.di.presentationModule
import com.example.remote.di.remoteDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CockTailsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CockTailsApp)
            modules(
                listOf(presentationModule, remoteDataModule, domainModule, localDataModule)
            )
        }
    }
}