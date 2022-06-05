package com.example.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.entity.CockTailEntity

@Dao
interface CockTailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCockTails(list: List<CockTailEntity>)

    @Query("SELECT * FROM cocktails")
    suspend fun getCockTails(): List<CockTailEntity>
}
