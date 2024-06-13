package com.alijan.laza.di

import com.alijan.laza.data.source.remote.APIService
import com.alijan.laza.data.source.remote.RemoteDataSource
import com.alijan.laza.data.source.remote.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: APIService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

}