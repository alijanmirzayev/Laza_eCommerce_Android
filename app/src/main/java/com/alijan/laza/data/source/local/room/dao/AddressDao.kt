package com.alijan.laza.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alijan.laza.data.dto.local.AddressLocalDTO

@Dao
interface AddressDao {
    @Query("SELECT * FROM address")
    suspend fun getAddressByLocal(): List<AddressLocalDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAddressToLocal(item: AddressLocalDTO)
}