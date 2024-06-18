package com.alijan.laza.domain.usecase

import com.alijan.laza.data.dto.local.WishlistLocalDTO
import com.alijan.laza.domain.repository.ProductRepository
import javax.inject.Inject

class GetItemWishlistByLocal @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun execute(item: WishlistLocalDTO) = productRepository.getItemWishlistByLocal(item)
}