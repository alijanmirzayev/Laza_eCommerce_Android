package com.alijan.laza.data.dto.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("wishlists")
data class WishlistLocalDTO(
    @PrimaryKey
    @ColumnInfo(name = "product_id") val productId: String,
    @ColumnInfo(name = "product_name") val productName: String?,
    @ColumnInfo(name = "product_price") val productPrice: String?,
    @ColumnInfo(name = "product_image") val productImage: String?,
)
