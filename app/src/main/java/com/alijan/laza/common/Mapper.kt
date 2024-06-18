package com.alijan.laza.common

import com.alijan.laza.data.dto.ProductDTO
import com.alijan.laza.data.dto.local.BasketLocalDTO

fun ProductDTO.toBasketLocalDTO(size: String, count: Int): BasketLocalDTO {
    return BasketLocalDTO(
        productId = id,
        productImage = productImage?.get(0),
        productPrice = productPrice,
        productName = productName,
        productCount = count,
        productSize = size,
    )
}