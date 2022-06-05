package com.example.remote.mappers

import com.example.domain.models.Drink
import com.example.domain.models.Drinks
import com.example.remote.dto.CockTailResponseDto
import com.example.remote.dto.DrinkDto

fun CockTailResponseDto.toDomain(): Drinks {
    return Drinks(
        drinks = drinks.map { it.toDomain() }
    )
}

fun DrinkDto.toDomain(): Drink {
    return Drink(
        idDrink, strDrink, strDrinkThumb
    )
}
