package com.alijan.laza.domain.usecase

import com.alijan.laza.data.dto.local.CardLocalDTO
import com.alijan.laza.domain.repository.ProductRepository
import javax.inject.Inject

class UpdateCardInformationToLocalUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun execute(item: CardLocalDTO) = productRepository.updateCardInformationToLocal(item)
}