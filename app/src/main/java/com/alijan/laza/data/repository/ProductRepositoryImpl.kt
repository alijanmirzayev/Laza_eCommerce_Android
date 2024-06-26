package com.alijan.laza.data.repository

import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.data.dto.BrandDTO
import com.alijan.laza.data.dto.ProductDTO
import com.alijan.laza.data.dto.local.AddressLocalDTO
import com.alijan.laza.data.dto.local.BasketLocalDTO
import com.alijan.laza.data.dto.local.CardLocalDTO
import com.alijan.laza.data.dto.local.WishlistLocalDTO
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

    override suspend fun getAllBasketByLocal(): NetworkResponse<List<BasketLocalDTO>> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.getAllBasketByLocal()
            return@withContext NetworkResponse.Success(response)
        }

    override suspend fun addBasketToLocal(item: BasketLocalDTO): NetworkResponse<Unit> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.addBasketToLocal(item)
            return@withContext NetworkResponse.Success(response)
        }

    override suspend fun deleteBasketToLocal(item: BasketLocalDTO): NetworkResponse<Unit> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.deleteBasketToLocal(item)
            return@withContext NetworkResponse.Success(response)
        }

    override suspend fun deleteAllBasketToLocal(): NetworkResponse<Unit> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.deleteAllBasketToLocal()
            return@withContext NetworkResponse.Success(response)
        }

    override suspend fun updateAddressInformationToLocal(item: AddressLocalDTO): NetworkResponse<Unit> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.updateAddressInformationToLocal(item)
            return@withContext NetworkResponse.Success(response)
        }

    override suspend fun getAddressInformationToLocal(): NetworkResponse<List<AddressLocalDTO>> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.getAddressInformationToLocal()
            return@withContext NetworkResponse.Success(response)
        }

    override suspend fun updateCardInformationToLocal(item: CardLocalDTO): NetworkResponse<Unit> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.updateCardInformationToLocal(item)
            return@withContext NetworkResponse.Success(response)
        }

    override suspend fun getCardInformationToLocal(): NetworkResponse<List<CardLocalDTO>> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.getCardInformationToLocal()
            return@withContext NetworkResponse.Success(response)
        }

    override suspend fun getAllWishlistByLocal(): NetworkResponse<List<WishlistLocalDTO>> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.getAllWishlistByLocal()
            return@withContext NetworkResponse.Success(response)
        }

    override suspend fun getItemWishlistByLocal(item: WishlistLocalDTO): NetworkResponse<WishlistLocalDTO?> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.getItemWishlistByLocal(item)
            return@withContext NetworkResponse.Success(response)
        }

    override suspend fun addItemWishlistToLocal(item: WishlistLocalDTO): NetworkResponse<Unit> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.addItemWishlistToLocal(item)
            return@withContext NetworkResponse.Success(response)
        }

    override suspend fun deleteItemWishlistToLocal(item: WishlistLocalDTO): NetworkResponse<Unit> =
        withContext(Dispatchers.IO) {
            val response = localDataSource.deleteItemWishlistToLocal(item)
            return@withContext NetworkResponse.Success(response)
        }
}