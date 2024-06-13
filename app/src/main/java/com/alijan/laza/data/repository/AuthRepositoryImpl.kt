package com.alijan.laza.data.repository

import android.util.Log
import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthRepository {
    override suspend fun signUp(email: String, password: String): NetworkResponse<AuthResult> =
        withContext(Dispatchers.IO) {
            try {
                val response = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                return@withContext NetworkResponse.Success(response)
            } catch (e: Exception) {
                return@withContext NetworkResponse.Error(e.localizedMessage)
            }
        }

    override suspend fun signIn(email: String, password: String): NetworkResponse<AuthResult> =
        withContext(Dispatchers.IO) {
            try {
                val response = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                return@withContext NetworkResponse.Success(response)
            } catch (e: Exception) {
                return@withContext NetworkResponse.Error(e.localizedMessage)

            }
        }

    override suspend fun confirmPasswordReset(
        code: String,
        newPassword: String
    ): NetworkResponse<Void> = withContext(Dispatchers.IO) {
        try {
            val response = firebaseAuth.confirmPasswordReset(code, newPassword).await()
            return@withContext NetworkResponse.Success(response)
        } catch (e: Exception) {
            return@withContext NetworkResponse.Error(e.localizedMessage)
        }
    }

    override suspend fun sendPasswordReset(email: String): NetworkResponse<Void> =
        withContext(Dispatchers.IO) {
            try {
                val response = firebaseAuth.sendPasswordResetEmail(email).await()
                return@withContext NetworkResponse.Success(response)
            } catch (e: Exception) {
                e.localizedMessage?.let {
                    return@withContext NetworkResponse.Error(e.localizedMessage)
                }
                return@withContext NetworkResponse.Error("Unknown Error")
            }
        }
}