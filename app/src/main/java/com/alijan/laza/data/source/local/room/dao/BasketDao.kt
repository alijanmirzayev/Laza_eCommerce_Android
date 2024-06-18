package com.alijan.laza.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alijan.laza.data.dto.local.BasketLocalDTO

@Dao
interface BasketDao {

    @Query("SELECT * FROM baskets")
    suspend fun getAllBasketByLocal(): List<BasketLocalDTO>

    @Query("DELETE FROM baskets")
    suspend fun deleteAllBasketByLocal()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBasketToLocal(item: BasketLocalDTO)

    @Delete
    suspend fun deleteBasketToLocal(item: BasketLocalDTO)

}