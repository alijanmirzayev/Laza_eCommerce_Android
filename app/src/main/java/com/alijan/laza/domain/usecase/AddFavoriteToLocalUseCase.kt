package com.alijan.laza.domain.usecase

import com.alijan.laza.data.dto.local.FavoriteLocalDTO
import com.alijan.laza.domain.repository.ProductRepository
import javax.inject.Inject

class AddFavoriteToLocalUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun execute(favoriteLocalDTO: FavoriteLocalDTO) = productRepository.addFavoriteToLocal(favoriteLocalDTO)
}