package com.alijan.laza.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alijan.laza.data.dto.local.FavoriteLocalDTO
import com.alijan.laza.data.source.local.room.dao.FavoriteDao

@Database(entities = [FavoriteLocalDTO::class], version = 1)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}