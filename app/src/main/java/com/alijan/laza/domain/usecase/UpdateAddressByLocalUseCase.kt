package com.alijan.laza.domain.usecase

import com.alijan.laza.data.dto.local.AddressLocalDTO
import com.alijan.laza.domain.repository.ProductRepository
import javax.inject.Inject

class UpdateAddressByLocalUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun execute(item: AddressLocalDTO) =
        productRepository.updateAddressInformationToLocal(item)
}