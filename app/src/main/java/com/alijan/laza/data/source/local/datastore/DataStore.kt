package com.alijan.laza.data.source.local.datastore

interface DataStore {

    suspend fun saveIsRegister(value: Boolean): Unit
    suspend fun getIsRegister(): Boolean?

}