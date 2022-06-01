package com.example.domain.repository

import com.example.domain.models.Drink

interface CockTailsRepository {
    suspend fun getCockTails(cocktail:String): List<Drink>
}