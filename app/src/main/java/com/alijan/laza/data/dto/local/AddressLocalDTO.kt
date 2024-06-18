package com.alijan.laza.data.dto.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("address")
data class AddressLocalDTO(
    @PrimaryKey
    @ColumnInfo("address_id") val addressId: String,
    @ColumnInfo("address_name") val addressName: String,
    @ColumnInfo("address_country") val addressCountry: String,
    @ColumnInfo("address_city") val addressCity: String,
    @ColumnInfo("address_phone_number") val addressPhoneNumber: String,
    @ColumnInfo("address_full_address") val addressFullAddress: String,
)