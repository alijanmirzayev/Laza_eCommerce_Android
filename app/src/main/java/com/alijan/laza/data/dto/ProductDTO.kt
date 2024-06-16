package com.alijan.laza.data.dto


import com.google.gson.annotations.SerializedName

data class ProductDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("productDescription")
    val productDescription: String?,
    @SerializedName("productImage")
    val productImage: List<String>?,
    @SerializedName("productName")
    val productName: String?,
    @SerializedName("productPrice")
    val productPrice: String?,
    @SerializedName("productSize")
    val productSize: List<String>?
)