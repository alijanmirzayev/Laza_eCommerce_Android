package com.alijan.laza.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alijan.laza.data.dto.local.FavoriteLocalDTO

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavoritesByLocal(): List<FavoriteLocalDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteToLocal(favoritesLocalDTO: FavoriteLocalDTO)

    @Delete
    fun deleteFavoriteToLocal(favoritesLocalDTO: FavoriteLocalDTO)

}