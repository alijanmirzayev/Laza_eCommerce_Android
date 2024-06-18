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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBasketToLocal(item: BasketLocalDTO)

    @Delete
    fun deleteBasketToLocal(item: BasketLocalDTO)

}