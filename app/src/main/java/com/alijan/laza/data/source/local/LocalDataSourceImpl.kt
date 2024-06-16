package com.alijan.laza.data.source.local

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.alijan.laza.data.dto.local.FavoriteLocalDTO
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

    override suspend fun getAllFavoritesByLocal(): List<FavoriteLocalDTO> {
        return roomDatabase.favoriteDao().getAllFavoritesByLocal()
    }

    override suspend fun addFavoriteToLocal(favoritesLocalDTO: FavoriteLocalDTO) {
        roomDatabase.favoriteDao().addFavoriteToLocal(favoritesLocalDTO)
    }

    override suspend fun deleteFavoriteToLocal(favoritesLocalDTO: FavoriteLocalDTO) {
        roomDatabase.favoriteDao().deleteFavoriteToLocal(favoritesLocalDTO)
    }


}