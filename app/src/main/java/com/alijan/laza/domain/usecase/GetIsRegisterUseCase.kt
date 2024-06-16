package com.alijan.laza.domain.usecase

import com.alijan.laza.domain.repository.AuthRepository
import javax.inject.Inject

class GetIsRegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun execute() = authRepository.getIsRegister()
}