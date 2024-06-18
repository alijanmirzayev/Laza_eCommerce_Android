package com.alijan.laza.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alijan.laza.data.dto.local.CardLocalDTO

@Dao
interface CardDao {
    @Query("SELECT * FROM cards")
    suspend fun getCardByLocal(): List<CardLocalDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateCardToLocal(item: CardLocalDTO)
}