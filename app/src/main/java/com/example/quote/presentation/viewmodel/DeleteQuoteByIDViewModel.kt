package com.example.quote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote.domain.usecase.DeleteQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteQuoteByIDViewModel @Inject constructor(
    private val deleteQuoteUseCase: DeleteQuoteUseCase,
) : ViewModel() {
    private val _quoteModelDelete = MutableStateFlow(0)
    val deletedId : StateFlow<Int> = _quoteModelDelete

    fun deleteQuoteById(id: Int) {
        viewModelScope.launch {
            _quoteModelDelete.value = deleteQuoteUseCase.deleteQuoteById(id)
        }
    }
}