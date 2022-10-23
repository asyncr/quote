package com.example.quote.data.local

import com.example.quote.domain.model.QuoteModel
import kotlinx.coroutines.flow.Flow

interface QuoteLocalDataSource {
    fun getQuotes(): Flow<List<QuoteModel>>
    fun getQuote(quoteId: Int): Flow<QuoteModel>
    fun getQuoteRandom(): Flow<QuoteModel>
    suspend fun deleteQuote(id: Int):Int
    suspend fun insertAll(quotes: List<QuoteModel>)
    suspend fun insert(quote: QuoteModel):Long
    suspend fun getLatestId(): Int
}