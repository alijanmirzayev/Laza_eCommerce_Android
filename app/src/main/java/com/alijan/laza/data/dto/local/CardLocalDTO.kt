package com.alijan.laza.data.dto.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cards")
data class CardLocalDTO(
    @PrimaryKey
    @ColumnInfo("card_id") val cardId: String,
    @ColumnInfo("card_owner") val cardOwner: String,
    @ColumnInfo("card_number") val cardNumber: String,
    @ColumnInfo("card_exp") val cardExp: String,
    @ColumnInfo("card_cvv") val cardCvv: String
)