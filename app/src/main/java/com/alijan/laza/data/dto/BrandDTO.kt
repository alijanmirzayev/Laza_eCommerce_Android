package com.alijan.laza.data.dto


import com.google.gson.annotations.SerializedName

data class BrandDTO(
    @SerializedName("brandName")
    val brandName: String?,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String?
)