package com.alijan.laza.presentation.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.domain.usecase.ConfirmResetUseCase
import com.alijan.laza.domain.usecase.GetIsRegisterUseCase
import com.alijan.laza.domain.usecase.SaveIsRegisterUseCase
import com.alijan.laza.domain.usecase.SendPasswordResetUseCase
import com.alijan.laza.domain.usecase.SignInUseCase
import com.alijan.laza.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val confirmPasswordResetUseCase: ConfirmResetUseCase,
    private val sendPasswordResetUseCase: SendPasswordResetUseCase,
    private val saveIsRegisterUseCase: SaveIsRegisterUseCase,
    private val getIsRegisterUseCase: GetIsRegisterUseCase
) : ViewModel() {

    private val _authResult = MutableLiveData<AuthUiState>()
    val authResult: LiveData<AuthUiState> get() = _authResult

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = AuthUiState.Loading
            when (val response = signUpUseCase.execute(email, password)) {
                is NetworkResponse.Error -> {
                    response.message?.let {
                        _authResult.value = AuthUiState.Error(it)
                    }
                }

                is NetworkResponse.Success -> {
                    _authResult.value = AuthUiState.Success
                }
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = AuthUiState.Loading
            when (val response = signInUseCase.execute(email, password)) {
                is NetworkResponse.Error -> {
                    response.message?.let {
                        _authResult.value = AuthUiState.Error(it)
                    }
                }

                is NetworkResponse.Success -> {
                    _authResult.value = AuthUiState.Success
                }
            }
        }
    }

    fun confirmResetPassword(code: String, newPassword: String) {
        viewModelScope.launch {
            _authResult.value = AuthUiState.Loading
            when (val response = confirmPasswordResetUseCase.execute(code, newPassword)) {
                is NetworkResponse.Error -> {
                    response.message?.let {
                        _authResult.value = AuthUiState.Error(it)
                    }
                }

                is NetworkResponse.Success -> {
                    _authResult.value = AuthUiState.Success
                }
            }
        }
    }

    fun resetPassword(email: String) {
        viewModelScope.launch {
            _authResult.value = AuthUiState.Loading
            when (val response = sendPasswordResetUseCase.execute(email)) {
                is NetworkResponse.Error -> {
                    response.message?.let {
                        _authResult.value = AuthUiState.Error(it)
                    }
                }

                is NetworkResponse.Success -> {
                    _authResult.value = AuthUiState.Success
                }
            }
        }
    }

    fun saveIsRegister(value: Boolean){
        viewModelScope.launch {
            saveIsRegisterUseCase.execute(value)
        }
    }

    fun getIsRegister(){
        viewModelScope.launch {
            _authResult.value = AuthUiState.Loading
            when(val response = getIsRegisterUseCase.execute()){
                is NetworkResponse.Error -> _authResult.value = AuthUiState.Error(response.message.toString())
                is NetworkResponse.Success -> _authResult.value = AuthUiState.Success
            }
        }
    }

}