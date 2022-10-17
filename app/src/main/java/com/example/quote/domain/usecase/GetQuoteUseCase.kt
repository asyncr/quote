package com.example.quote.domain.usecase

import com.example.quote.domain.QuoteRepository
import javax.inject.Inject

class GetQuoteUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend fun getQuote(quoteId: Int) = quoteRepository.getQuote(quoteId)
}