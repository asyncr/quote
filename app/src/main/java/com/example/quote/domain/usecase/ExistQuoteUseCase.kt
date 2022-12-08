package com.example.quote.domain.usecase

import com.example.quote.domain.QuoteRepository
import javax.inject.Inject

class ExistQuoteUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend fun existQuote(id: Int): Boolean {
        return quoteRepository.existQuote(id)
    }
}