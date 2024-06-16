package com.alijan.laza.data.repository

import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.data.dto.BrandDTO
import com.alijan.laza.data.dto.ProductDTO
import com.alijan.laza.data.dto.local.FavoriteLocalDTO
import com.alijan.laza.data.source.local.LocalDataSource
import com.alijan.laza.data.source.remote.RemoteDataSource
import com.alijan.laza.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    ProductRepository {
    override suspend fun getAllBrands(): NetworkResponse<List<BrandDTO>> =
        withContext(Dispatchers.IO) {
            val response = remoteDataSource.getAllBrands()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@withContext NetworkResponse.Success(it)
                }
            }
            return@withContext NetworkResponse.Error(response.message())
        }

    override suspend fun getNewArrivalProduct(): NetworkResponse<List<ProductDTO>> =
        withContext(Dispatchers.IO) {
            val response = remoteDataSource.getNewArrivalProduct()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@withContext NetworkResponse.Success(it)
                }
            }
            return@withContext NetworkResponse.Error(response.message())
        }

    override suspend fun getProductById(id: String): NetworkResponse<ProductDTO> =
        withContext(Dispatchers.IO) {
            val response = remoteDataSource.getProductById(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@withContext NetworkResponse.Success(it)
                }
            }
            return@withContext NetworkResponse.Error(response.message())
        }

    override suspend fun getAllFavoritesByLocal(): NetworkResponse<List<FavoriteLocalDTO>> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.getAllFavoritesByLocal()
            return@withContext NetworkResponse.Success(response)
        }

    override suspend fun addFavoriteToLocal(favoritesLocalDTO: FavoriteLocalDTO): NetworkResponse<Unit> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.addFavoriteToLocal(favoritesLocalDTO)
            return@withContext NetworkResponse.Success(response)
        }

    override suspend fun deleteFavoriteToLocal(favoritesLocalDTO: FavoriteLocalDTO): NetworkResponse<Unit> =
    withContext(Dispatchers.IO) {
        val response = localDataSource.deleteFavoriteToLocal(favoritesLocalDTO)
        return@withContext NetworkResponse.Success(response)
    }
}