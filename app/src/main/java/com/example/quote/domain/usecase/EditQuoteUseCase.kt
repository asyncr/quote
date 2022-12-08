package com.example.quote.domain.usecase

import com.example.quote.domain.QuoteRepository
import com.example.quote.domain.model.QuoteModel
import javax.inject.Inject

class EditQuoteUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend fun editQuote(quoteModel: QuoteModel) =
        quoteRepository.editQuote(quoteModel)

}
