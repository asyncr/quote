package com.example.quote.domain.usecase

import com.example.quote.domain.QuoteRepository
import javax.inject.Inject

class DeleteQuoteByIdUserCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    suspend fun deleteQuote(id: Int) = quoteRepository.deleteQuote(id)
}