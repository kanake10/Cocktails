package com.example.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktails")
data class CockTailEntity (
    @PrimaryKey(autoGenerate = false)
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
        )