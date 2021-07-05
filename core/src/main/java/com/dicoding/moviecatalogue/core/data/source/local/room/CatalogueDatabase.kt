package com.dicoding.moviecatalogue.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatalogueDatabase : RoomDatabase() {

    abstract fun catalogueDao(): com.dicoding.moviecatalogue.core.data.source.local.room.CatalogueDao
}