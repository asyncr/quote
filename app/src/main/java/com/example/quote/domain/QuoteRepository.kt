package com.example.quote.domain

import com.example.quote.data.remote.QuoteApiResponse
import com.example.quote.domain.model.QuoteModel
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend fun getQuotes(token: String): Flow<QuoteApiResponse?>
    suspend fun getQuoteRandom(): Flow<QuoteModel>
    suspend fun getQuote(quoteId:Int): Flow<QuoteModel?>
    suspend fun editQuote(quoteModel: QuoteModel)
    suspend fun addQuote(quoteModel: QuoteModel)
    suspend fun deleteQuote(quoteId: Int):Int
}
