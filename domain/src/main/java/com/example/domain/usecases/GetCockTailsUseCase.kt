package com.example.domain.usecases

import com.example.domain.models.Drink
import com.example.domain.repository.CockTailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetCockTailsUseCase(private val repository: CockTailsRepository) {
    suspend operator fun invoke(cocktail: String): Flow<List<Drink>> {
        return flowOf(repository.getCockTails(cocktail))
    }
}