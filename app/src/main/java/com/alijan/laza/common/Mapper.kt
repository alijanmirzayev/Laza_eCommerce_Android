package com.alijan.laza.common

import com.alijan.laza.data.dto.ProductDTO
import com.alijan.laza.data.dto.local.FavoriteLocalDTO

fun ProductDTO.toFavoriteLocalDTO(size: String, count: Int): FavoriteLocalDTO {
    return FavoriteLocalDTO(
        productId = id,
        productImage = productImage?.get(0),
        productPrice = productPrice,
        productName = productName,
        productCount = count,
        productSize = size,
    )
}