package com.alijan.laza.data.dto.local

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorites")
data class FavoriteLocalDTO(
    @PrimaryKey
    @ColumnInfo(name = "product_id") val productId: String,
    @ColumnInfo(name = "product_name") val productName: String?,
    @ColumnInfo(name = "product_price") val productPrice: String?,
    @ColumnInfo(name = "product_image") val productImage: String?,
    @ColumnInfo(name = "product_size") val productSize: String?,
    @ColumnInfo(name = "product_count") val productCount: Int?,
)
