package com.alijan.laza.domain.repository

import com.alijan.laza.common.NetworkResponse
import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun signUp(email: String, password: String): NetworkResponse<AuthResult>

    suspend fun signIn(email: String, password: String): NetworkResponse<AuthResult>

    suspend fun confirmPasswordReset(code: String, newPassword: String): NetworkResponse<Void>

    suspend fun sendPasswordReset(email: String): NetworkResponse<Void>

}