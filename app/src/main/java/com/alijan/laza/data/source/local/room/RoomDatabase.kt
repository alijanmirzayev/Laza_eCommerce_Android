package com.alijan.laza.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alijan.laza.data.dto.local.AddressLocalDTO
import com.alijan.laza.data.dto.local.BasketLocalDTO
import com.alijan.laza.data.dto.local.CardLocalDTO
import com.alijan.laza.data.dto.local.WishlistLocalDTO
import com.alijan.laza.data.source.local.room.dao.AddressDao
import com.alijan.laza.data.source.local.room.dao.BasketDao
import com.alijan.laza.data.source.local.room.dao.CardDao
import com.alijan.laza.data.source.local.room.dao.WishlistDao

@Database(
    entities = [BasketLocalDTO::class, AddressLocalDTO::class, CardLocalDTO::class, WishlistLocalDTO::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun basketDao(): BasketDao
    abstract fun addressDao(): AddressDao
    abstract fun cardDao(): CardDao
    abstract fun wishlistDao(): WishlistDao
}