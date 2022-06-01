package com.example.remote.mappers

import com.example.cache.entity.CockTailEntity
import com.example.domain.models.Drink

fun Drink.toEntity(): CockTailEntity{
    return CockTailEntity(
        idDrink,
        strDrink,
        strDrinkThumb
    )
}