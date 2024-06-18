package com.alijan.laza.data.source.local

import com.alijan.laza.data.dto.local.AddressLocalDTO
import com.alijan.laza.data.dto.local.BasketLocalDTO
import com.alijan.laza.data.dto.local.CardLocalDTO

interface LocalDataSource {
    suspend fun saveIsRegister(value: Boolean)
    suspend fun getIsRegister(): Boolean?
    suspend fun getAllBasketByLocal(): List<BasketLocalDTO>
    suspend fun addBasketToLocal(favoritesLocalDTO: BasketLocalDTO)
    suspend fun deleteBasketToLocal(favoritesLocalDTO: BasketLocalDTO)
    suspend fun deleteAllBasketToLocal()
    suspend fun updateAddressInformationToLocal(item: AddressLocalDTO)
    suspend fun getAddressInformationToLocal(): List<AddressLocalDTO>
    suspend fun updateCardInformationToLocal(item: CardLocalDTO)
    suspend fun getCardInformationToLocal(): List<CardLocalDTO>

}