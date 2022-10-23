package com.example.quote.domain.usecase

import com.example.quote.domain.QuoteRepository
import com.example.quote.domain.model.QuoteModel
import javax.inject.Inject

class DeleteQuoteUseCase@Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    suspend fun deleteQuote(id: Int):Int = quoteRepository.deleteQuote(id)
}