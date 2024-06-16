package com.alijan.laza.domain.usecase

import com.alijan.laza.domain.repository.AuthRepository
import javax.inject.Inject

class SaveIsRegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun execute(value: Boolean) = authRepository.saveIsRegister(value)
}