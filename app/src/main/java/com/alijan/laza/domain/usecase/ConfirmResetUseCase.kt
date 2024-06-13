package com.alijan.laza.domain.usecase

import com.alijan.laza.domain.repository.AuthRepository
import javax.inject.Inject

class ConfirmResetUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun execute(code: String, newPassword: String) =
        authRepository.confirmPasswordReset(code, newPassword)

}