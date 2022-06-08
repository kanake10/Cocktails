package com.example.cache.di

import androidx.room.Room
import com.example.cache.db.CockTailsDatabase
import com.example.core.Constants.COCKTAILS_DB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localDataModule = module {
    single {
        Room.databaseBuilder(androidApplication(), CockTailsDatabase::class.java, COCKTAILS_DB)
            .fallbackToDestructiveMigration().build()
    }

    single { get<CockTailsDatabase>().cockTailDao() }
}
