package com.alijan.laza.presentation.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.data.dto.local.CardLocalDTO
import com.alijan.laza.domain.usecase.GetCardInformationToLocalUseCase
import com.alijan.laza.domain.usecase.UpdateCardInformationToLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getCardInformationToLocalUseCase: GetCardInformationToLocalUseCase,
    private val updateCardInformationToLocalUseCase: UpdateCardInformationToLocalUseCase
) : ViewModel() {

    private var _card = MutableLiveData<PaymentUiState>()
    val card: LiveData<PaymentUiState> get() = _card

    init {
        getCardInformation()
    }

    private fun getCardInformation() {
        viewModelScope.launch {
            _card.value = PaymentUiState.Loading
            try {
                when (val resp = getCardInformationToLocalUseCase.execute()) {
                    is NetworkResponse.Error -> _card.value =
                        resp.message?.let { PaymentUiState.Error(it) }

                    is NetworkResponse.Success -> _card.value =
                        PaymentUiState.Success(resp.data!!)
                }
            } catch (e: Exception) {
                _card.value = PaymentUiState.Error(e.localizedMessage)
            }
        }
    }

    fun updateCardInformationByLocal(updatedItem: CardLocalDTO) {
        viewModelScope.launch {
            updateCardInformationToLocalUseCase.execute(updatedItem)
        }
    }

}