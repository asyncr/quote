package com.example.quote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote.domain.model.QuoteModel
import com.example.quote.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel
@Inject constructor(
    private val getQuoteRandomUseCase: GetQuoteRandomUseCase,
    private val addQuoteUseCase: AddQuoteUseCase,
    private val getAllQuoteUseCase: GetAllQuoteUseCase,
    private val deleteQuoteUseCase: DeleteQuoteUseCase,
    private val getLatestIdUseCase: GetLatestIdUseCase
): ViewModel() {

    private val quoteModelRandomMutableStateFlow = MutableStateFlow(QuoteModel(0,"",""))
    private val quoteModelListMutableStateFlow = MutableStateFlow<List<QuoteModel>>(listOf())
    private val quoteModelDelete = MutableStateFlow(0)
    private val quoteModelAdd = MutableStateFlow(0L)
    private val quoteModelLatestId = MutableStateFlow(0)

    val quoteModelLatestIdMutableStateFlow: StateFlow<Int> = quoteModelLatestId
    val quoteModelAddRowId: StateFlow<Long> = quoteModelAdd
    val quoteModelDeleteRowId : StateFlow<Int> = quoteModelDelete
    val quoteModel: StateFlow<QuoteModel> = quoteModelRandomMutableStateFlow
    val quoteModelList: StateFlow<List<QuoteModel>> = quoteModelListMutableStateFlow


    fun randomQuote() {
        viewModelScope.launch {
            quoteModelRandomMutableStateFlow.value = getQuoteRandomUseCase.getQuoteRandom().first()
            //_quoteModel.value = GetQuoteUseCase(quoteDAO).getQuote(1).first()
        }
    }

    fun addQuote(quoteModel: QuoteModel) {
        viewModelScope.launch {
            quoteModelAdd.value = addQuoteUseCase.addQuote(quoteModel)
        }
    }

    fun getAllQuote() {
        viewModelScope.launch {
            quoteModelListMutableStateFlow.value = getAllQuoteUseCase.getAllQuote().first()
            val value = quoteModelListMutableStateFlow.value
            println("Value: $value")
        }
    }

    fun deleteQuote(id:Int) {
        viewModelScope.launch {
            quoteModelDelete.value = deleteQuoteUseCase.deleteQuote(id)
        }
    }

    fun getLatestId() {
        viewModelScope.launch {
            quoteModelLatestId.value = getLatestIdUseCase.getLatestId()
        }
    }

}