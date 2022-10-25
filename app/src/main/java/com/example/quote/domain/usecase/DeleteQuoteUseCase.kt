package com.example.quote.domain.usecase

import com.example.quote.domain.QuoteRepository
import javax.inject.Inject

class DeleteQuoteUseCase@Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    suspend fun deleteQuoteById(id: Int) = quoteRepository.deleteQuote(id)
}