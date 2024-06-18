package com.alijan.laza.data.source.local

import com.alijan.laza.data.dto.local.AddressLocalDTO
import com.alijan.laza.data.dto.local.BasketLocalDTO
import com.alijan.laza.data.dto.local.CardLocalDTO
import com.alijan.laza.data.dto.local.WishlistLocalDTO

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
    suspend fun getAllWishlistByLocal(): List<WishlistLocalDTO>
    suspend fun getItemWishlistByLocal(item: WishlistLocalDTO): WishlistLocalDTO?
    suspend fun addItemWishlistToLocal(item: WishlistLocalDTO)
    suspend fun deleteItemWishlistToLocal(item: WishlistLocalDTO)

}