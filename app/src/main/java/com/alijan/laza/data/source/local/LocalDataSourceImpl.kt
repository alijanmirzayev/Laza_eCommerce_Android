package com.alijan.laza.data.source.local

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.alijan.laza.data.dto.local.AddressLocalDTO
import com.alijan.laza.data.dto.local.BasketLocalDTO
import com.alijan.laza.data.dto.local.CardLocalDTO
import com.alijan.laza.data.source.local.room.RoomDatabase
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dataStore: androidx.datastore.core.DataStore<Preferences>,
    private val roomDatabase: RoomDatabase
) : LocalDataSource {

    private object PreferencesKeys {
        val REGISTER_KEY = booleanPreferencesKey("isRegister")
    }

    override suspend fun saveIsRegister(value: Boolean) {
        dataStore.edit {
            it[PreferencesKeys.REGISTER_KEY] = value
        }
    }

    override suspend fun getIsRegister(): Boolean? {
        val preferences = dataStore.data.first()
        return preferences[PreferencesKeys.REGISTER_KEY] ?: null
    }

    override suspend fun getAllBasketByLocal(): List<BasketLocalDTO> {
        return roomDatabase.basketDao().getAllBasketByLocal()
    }

    override suspend fun addBasketToLocal(item: BasketLocalDTO) {
        roomDatabase.basketDao().addBasketToLocal(item)
    }

    override suspend fun deleteBasketToLocal(item: BasketLocalDTO) {
        roomDatabase.basketDao().deleteBasketToLocal(item)
    }

    override suspend fun deleteAllBasketToLocal() {
        roomDatabase.basketDao().deleteAllBasketByLocal()
    }

    override suspend fun updateAddressInformationToLocal(item: AddressLocalDTO) {
        roomDatabase.addressDao().updateAddressToLocal(item)
    }

    override suspend fun getAddressInformationToLocal(): List<AddressLocalDTO> {
        return roomDatabase.addressDao().getAddressByLocal()
    }

    override suspend fun updateCardInformationToLocal(item: CardLocalDTO) {
        roomDatabase.cardDao().updateCardToLocal(item)
    }

    override suspend fun getCardInformationToLocal(): List<CardLocalDTO> {
        return roomDatabase.cardDao().getCardByLocal()
    }


}