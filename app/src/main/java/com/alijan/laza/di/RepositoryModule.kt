package com.alijan.laza.di

import com.alijan.laza.data.repository.AuthRepositoryImpl
import com.alijan.laza.data.repository.ProductRepositoryImpl
import com.alijan.laza.data.source.remote.RemoteDataSource
import com.alijan.laza.domain.repository.AuthRepository
import com.alijan.laza.domain.repository.ProductRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Singleton
    @Provides
    fun provideProductRepository(remoteDataSource: RemoteDataSource): ProductRepository {
        return ProductRepositoryImpl(remoteDataSource)
    }
}