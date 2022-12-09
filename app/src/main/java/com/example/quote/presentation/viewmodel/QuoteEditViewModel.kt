package com.example.quote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote.domain.model.QuoteModel
import com.example.quote.domain.usecase.EditQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuoteEditViewModel
@Inject constructor(private val editQuoteUseCase: EditQuoteUseCase) : ViewModel() {
    private val quoteModelInitial =
        QuoteModel(id = 0, quote = "Solo se que no se nada", author = "Socrates")
    private val _quoteEditMutableStateFlow = MutableStateFlow(quoteModelInitial)
    val quoteModel: StateFlow<QuoteModel> = _quoteEditMutableStateFlow


    fun editQuote(quoteModel: QuoteModel) {
        viewModelScope.launch {
            editQuoteUseCase.editQuote(quoteModel = quoteModel)
        }
    }
}