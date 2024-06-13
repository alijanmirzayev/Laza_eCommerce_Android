package com.alijan.laza.domain.usecase

import com.alijan.laza.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun execute(email: String, password: String) = authRepository.signUp(email, password)

}