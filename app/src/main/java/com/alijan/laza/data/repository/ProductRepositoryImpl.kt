package com.alijan.laza.data.repository

import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.data.source.remote.RemoteDataSource
import com.alijan.laza.data.source.remote.dto.BrandDTO
import com.alijan.laza.data.source.remote.dto.ProductDTO
import com.alijan.laza.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
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
}