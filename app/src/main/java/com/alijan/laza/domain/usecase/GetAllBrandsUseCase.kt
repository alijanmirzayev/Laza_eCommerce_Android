package com.alijan.laza.domain.usecase

import com.alijan.laza.domain.repository.ProductRepository
import javax.inject.Inject

class GetAllBrandsUseCase @Inject constructor(private val productRepository: ProductRepository)  {

    suspend fun execute() = productRepository.getAllBrands()
}