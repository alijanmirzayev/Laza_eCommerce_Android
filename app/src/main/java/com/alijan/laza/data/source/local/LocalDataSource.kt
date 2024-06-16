package com.alijan.laza.data.source.local

import com.alijan.laza.data.dto.local.FavoriteLocalDTO

interface LocalDataSource {
    suspend fun saveIsRegister(value: Boolean): Unit
    suspend fun getIsRegister(): Boolean?

    suspend fun getAllFavoritesByLocal(): List<FavoriteLocalDTO>

    suspend fun addFavoriteToLocal(favoritesLocalDTO: FavoriteLocalDTO)
    suspend fun deleteFavoriteToLocal(favoritesLocalDTO: FavoriteLocalDTO)

}