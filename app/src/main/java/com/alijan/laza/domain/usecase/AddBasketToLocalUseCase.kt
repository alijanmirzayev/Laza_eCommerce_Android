package com.alijan.laza.domain.usecase

import com.alijan.laza.data.dto.local.BasketLocalDTO
import com.alijan.laza.domain.repository.ProductRepository
import javax.inject.Inject

class AddBasketToLocalUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun execute(basketLocalDTO: BasketLocalDTO) = productRepository.addBasketToLocal(basketLocalDTO)
}