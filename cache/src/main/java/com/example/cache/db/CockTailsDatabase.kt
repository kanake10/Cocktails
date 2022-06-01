package com.example.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cache.dao.CockTailDao
import com.example.cache.entity.CockTailEntity

@Database(entities = [ CockTailEntity::class], version = 1, exportSchema = false)
abstract class CockTailsDatabase : RoomDatabase() {

    abstract fun cockTailDao(): CockTailDao
}