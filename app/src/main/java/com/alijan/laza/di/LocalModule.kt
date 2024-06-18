package com.alijan.laza.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.alijan.laza.data.source.local.LocalDataSource
import com.alijan.laza.data.source.local.LocalDataSourceImpl
import com.alijan.laza.data.source.local.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RoomDatabase::class.java, "room_db").build()

    @Singleton
    @Provides
    fun provideFavoriteDao(roomDatabase: RoomDatabase) = roomDatabase.basketDao()

    @Singleton
    @Provides
    fun provideAddressDao(roomDatabase: RoomDatabase) = roomDatabase.addressDao()

    @Singleton
    @Provides
    fun provideCardDao(roomDatabase: RoomDatabase) = roomDatabase.cardDao()

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(produceFile = {
            context.preferencesDataStoreFile("settings")
        })

    @Singleton
    @Provides
    fun provideLocalDataSourceImpl(
        dataStore: DataStore<Preferences>,
        roomDatabase: RoomDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(dataStore, roomDatabase)
    }

}