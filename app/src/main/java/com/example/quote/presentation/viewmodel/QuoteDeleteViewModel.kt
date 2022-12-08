package com.example.quote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote.domain.model.QuoteModel
import com.example.quote.domain.usecase.DeleteQuoteByIdUserCase
import com.example.quote.domain.usecase.ExistQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteDeleteViewModel
@Inject constructor(
    private val existQuoteUseCase: ExistQuoteUseCase,
    private val deleteQuoteByIdUserCase: DeleteQuoteByIdUserCase
) : ViewModel() {
    private val _existQuoteMutableStateFlow = MutableStateFlow(false)
    val existQuote = _existQuoteMutableStateFlow

    private val quoteModelInitial = QuoteModel(id = 0, quote = "Solo se que no se nada", author = "Socrates")
    private val _deleteQuoteMutableStateFlow = MutableStateFlow(quoteModelInitial)
    val deleteQuote = _deleteQuoteMutableStateFlow

    fun existQuote(id: Int) {
        viewModelScope.launch {
            _existQuoteMutableStateFlow.value = existQuoteUseCase.existQuote(id)
        }
    }

    fun deleteQuote(id: Int) {
        viewModelScope.launch {
            deleteQuoteByIdUserCase.deleteQuote(id)
        }
    }


}