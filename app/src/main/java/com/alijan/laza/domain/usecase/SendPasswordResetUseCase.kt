package com.alijan.laza.domain.usecase

import com.alijan.laza.domain.repository.AuthRepository
import javax.inject.Inject

class SendPasswordResetUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun execute(email: String) = authRepository.sendPasswordReset(email)

}