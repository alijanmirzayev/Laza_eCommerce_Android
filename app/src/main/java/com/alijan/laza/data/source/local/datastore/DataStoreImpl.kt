package com.alijan.laza.data.source.local.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreImpl @Inject constructor(private val dataStore: androidx.datastore.core.DataStore<Preferences>) :
    DataStore {

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


}