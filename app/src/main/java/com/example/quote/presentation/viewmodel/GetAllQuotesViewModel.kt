package com.example.quote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote.domain.model.QuoteModel
import com.example.quote.domain.usecase.GetAllQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetAllQuotesViewModel @Inject constructor(
    private val getAllQuoteUseCase: GetAllQuoteUseCase,
) : ViewModel() {
    private val _quoteModelListMutableStateFlow = MutableStateFlow<List<QuoteModel>>(listOf())
    val quoteModelList: StateFlow<List<QuoteModel>> = _quoteModelListMutableStateFlow

    fun getAllQuotes() {
        viewModelScope.launch {
            _quoteModelListMutableStateFlow.value = getAllQuoteUseCase.getAllQuote().first()
        }
    }
}