package com.example.quote.domain.usecase

import com.example.quote.domain.QuoteRepository
import javax.inject.Inject

class GetLatestIdUseCase@Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    suspend fun getLatestId() = quoteRepository.getLatestId()
}