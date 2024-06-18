package com.alijan.laza.domain.usecase

import com.alijan.laza.domain.repository.ProductRepository
import javax.inject.Inject

class GetAllWishlistByLocalUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun execute() = productRepository.getAllWishlistByLocal()
}