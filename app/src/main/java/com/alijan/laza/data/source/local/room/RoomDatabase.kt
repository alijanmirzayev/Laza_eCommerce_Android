package com.alijan.laza.data.source.local.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.alijan.laza.data.dto.local.AddressLocalDTO
import com.alijan.laza.data.dto.local.BasketLocalDTO
import com.alijan.laza.data.source.local.room.dao.AddressDao
import com.alijan.laza.data.source.local.room.dao.BasketDao

@Database(
    entities = [BasketLocalDTO::class, AddressLocalDTO::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun basketDao(): BasketDao
    abstract fun addressDao(): AddressDao
}