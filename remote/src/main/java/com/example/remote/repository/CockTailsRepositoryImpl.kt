package com.example.remote.repository

import com.example.cache.dao.CockTailDao
import com.example.domain.models.Drink
import com.example.domain.repository.CockTailsRepository
import com.example.remote.api.CockTailsApi
import com.example.remote.mappers.toDomain
import com.example.remote.mappers.toEntity

/**
 * @param api
 * @param dao
 */
class CockTailsRepositoryImpl(private val api: CockTailsApi, private val dao: CockTailDao) :
    CockTailsRepository {

    override suspend fun getCockTails(cocktail: String): List<Drink> {
        val cocktails = dao.getCockTails()

        if (cocktails.size > 1) {
            return cocktails.map { it.toDomain() }
        } else {
            val cocktails = api.getCockTails(cocktail)
            dao.saveCockTails(cocktails.drinks.map { it.toDomain().toEntity() })
            return cocktails.drinks.map { it.toDomain() }
        }
    }
}
