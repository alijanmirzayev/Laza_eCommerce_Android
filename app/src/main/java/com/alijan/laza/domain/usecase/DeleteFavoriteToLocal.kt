package com.alijan.laza.domain.usecase

import com.alijan.laza.data.dto.local.FavoriteLocalDTO
import com.alijan.laza.domain.repository.ProductRepository
import javax.inject.Inject

class DeleteFavoriteToLocalUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun execute(favoriteLocalDTO: FavoriteLocalDTO) = productRepository.deleteFavoriteToLocal(favoriteLocalDTO)
}