package com.alijan.laza.domain.repository

import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.data.dto.BrandDTO
import com.alijan.laza.data.dto.ProductDTO
import com.alijan.laza.data.dto.local.FavoriteLocalDTO

interface ProductRepository {

    suspend fun getAllBrands(): NetworkResponse<List<BrandDTO>>
    suspend fun getNewArrivalProduct(): NetworkResponse<List<ProductDTO>>
    suspend fun getProductById(id: String): NetworkResponse<ProductDTO>

    suspend fun getAllFavoritesByLocal(): NetworkResponse<List<FavoriteLocalDTO>>

    suspend fun addFavoriteToLocal(favoritesLocalDTO: FavoriteLocalDTO): NetworkResponse<Unit>
    suspend fun deleteFavoriteToLocal(favoritesLocalDTO: FavoriteLocalDTO): NetworkResponse<Unit>


}