package com.example.remote.repository

import com.example.cache.dao.CockTailDao
import com.example.domain.models.Drink
import com.example.domain.repository.CockTailsRepository
import com.example.remote.api.CockTailsApi
import com.example.remote.mappers.toDomain
import com.example.remote.mappers.toEntity

class CockTailsRepositoryImpl(private val api: CockTailsApi, private val dao: CockTailDao) :
    CockTailsRepository {

    override suspend fun getCockTails(cocktail: String): List<Drink> {
        val characters = dao.getCockTails()

        if (characters.size > 1) {
            return characters.map { it.toDomain() }
        } else {
            val characters = api.getCockTails(cocktail)
            dao.saveCockTails(characters.drinks.map { it.toDomain().toEntity() })
            return characters.drinks.map { it.toDomain() }
        }
    }


}