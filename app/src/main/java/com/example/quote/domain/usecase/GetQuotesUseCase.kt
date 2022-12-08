package com.example.quote.domain.usecase

import com.example.quote.data.remote.QuoteApiResponse
import com.example.quote.domain.QuoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetQuotesUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend fun getQuotes(token: String): Flow<QuoteApiResponse?> = quoteRepository.getQuotes(token)
}