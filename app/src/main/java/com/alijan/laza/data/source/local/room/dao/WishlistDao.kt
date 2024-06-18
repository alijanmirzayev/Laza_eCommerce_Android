package com.alijan.laza.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alijan.laza.data.dto.local.WishlistLocalDTO

@Dao
interface WishlistDao {
    @Query("SELECT * FROM wishlists")
    suspend fun getAllWishlistByLocal(): List<WishlistLocalDTO>

    @Query("SELECT * FROM wishlists WHERE product_id = :product_id")
    suspend fun getItemWishlistByLocal(product_id: String): WishlistLocalDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemWishlistToLocal(item: WishlistLocalDTO)

    @Delete
    suspend fun deleteItemWishlistToLocal(item: WishlistLocalDTO)
}