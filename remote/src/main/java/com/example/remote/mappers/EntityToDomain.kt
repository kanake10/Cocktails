package com.example.remote.mappers

import com.example.cache.entity.CockTailEntity
import com.example.domain.models.Drink

fun CockTailEntity.toDomain(): Drink {
    return Drink(
        idDrink, strDrink, strDrinkThumb
    )
}
