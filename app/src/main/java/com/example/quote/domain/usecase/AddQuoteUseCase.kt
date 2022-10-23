package com.example.quote.domain.usecase

import com.example.quote.domain.QuoteRepository
import com.example.quote.domain.model.QuoteModel
import javax.inject.Inject

class AddQuoteUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    suspend fun addQuote(quoteModel: QuoteModel):Long = quoteRepository.addQuote(quoteModel)
}