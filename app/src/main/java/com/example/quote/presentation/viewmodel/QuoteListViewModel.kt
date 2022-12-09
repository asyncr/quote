package com.example.quote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote.data.remote.QuoteApiResponse
import com.example.quote.domain.model.QuoteModel
import com.example.quote.domain.usecase.GetQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuoteListViewModel
@Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase
) : ViewModel() {
    val quote = QuoteModel(0, "Solo se que no se nada", "Socrates")
    private var _quotes = MutableStateFlow(QuoteApiResponse(false, "", listOf(quote)))
    val quotes: StateFlow<QuoteApiResponse> = _quotes

    fun getQuotes(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val quoteResponse = getQuotesUseCase.getQuotes(token).first()
            quoteResponse.let {
                _quotes.value = it!!
            }
            println("QuoteListViewModel: $quoteResponse")
        }
    }
}