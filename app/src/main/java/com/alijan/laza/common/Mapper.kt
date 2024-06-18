package com.alijan.laza.common

import com.alijan.laza.data.dto.ProductDTO
import com.alijan.laza.data.dto.local.BasketLocalDTO
import com.alijan.laza.data.dto.local.WishlistLocalDTO

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

fun ProductDTO.toWishlistLocalDTO(): WishlistLocalDTO {
    return WishlistLocalDTO(
        productId = id,
        productName = productName,
        productPrice = productPrice,
        productImage = productImage?.get(0)
    )
}