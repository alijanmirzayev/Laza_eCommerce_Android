package com.alijan.laza.data.source.remote

import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.data.dto.BrandDTO
import com.alijan.laza.data.dto.ProductDTO
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: APIService) :
    RemoteDataSource {

    override suspend fun getAllBrands(): Response<List<BrandDTO>> = apiService.getAllBrands()
    override suspend fun getNewArrivalProduct(): Response<List<ProductDTO>> = apiService.getNewArrivalProducts()
    override suspend fun getProductById(id: String): Response<ProductDTO> = apiService.getProductById(id)

}