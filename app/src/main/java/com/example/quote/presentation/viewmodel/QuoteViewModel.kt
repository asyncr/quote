package com.example.quote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote.data.QuoteProvider
import com.example.quote.domain.model.QuoteModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {

    private val _quoteModel = MutableStateFlow(QuoteModel("", ""))
    val quoteModel: StateFlow<QuoteModel> = _quoteModel //Maneja los cambios de estado
    //Ante un cambio
    //---  Load data from a suspend fun and mutate state

    fun randomQuote() {
        //Limitar la visibilidad al ViewModel
        viewModelScope.launch {
            _quoteModel.value = QuoteProvider.random() //Cambia el estado
        }
    }

}