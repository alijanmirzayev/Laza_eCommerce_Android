package com.alijan.laza.data.source.local

import com.alijan.laza.data.dto.local.AddressLocalDTO
import com.alijan.laza.data.dto.local.BasketLocalDTO

interface LocalDataSource {
    suspend fun saveIsRegister(value: Boolean)
    suspend fun getIsRegister(): Boolean?
    suspend fun getAllBasketByLocal(): List<BasketLocalDTO>
    suspend fun addBasketToLocal(favoritesLocalDTO: BasketLocalDTO)
    suspend fun deleteBasketToLocal(favoritesLocalDTO: BasketLocalDTO)
    suspend fun updateAddressInformationToLocal(item: AddressLocalDTO)
    suspend fun getAddressInformationToLocal(): List<AddressLocalDTO>

}