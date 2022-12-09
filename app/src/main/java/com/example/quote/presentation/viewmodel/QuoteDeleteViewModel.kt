package com.example.quote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote.domain.usecase.DeleteQuoteByIdUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteDeleteViewModel
@Inject constructor(
    private val deleteQuoteByIdUserCase: DeleteQuoteByIdUserCase,
) : ViewModel() {

    private var _deleteQuoteMutableStateFlow = MutableStateFlow(-1)
    val deleteQuote = _deleteQuoteMutableStateFlow

    fun deleteQuoteById(id: Int) {
        viewModelScope.launch {
            _deleteQuoteMutableStateFlow.value = deleteQuoteByIdUserCase.deleteQuote(id)
        }
    }

}