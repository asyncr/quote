package com.example.quote.domain

import com.example.quote.domain.model.QuoteModel
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    // suspend fun getQuotes(): Flow<QuoteResponse?>
    suspend fun getLatestId(): Int
    suspend fun getQuoteRandom(): Flow<QuoteModel>
    suspend fun getQuote(quoteId:Int): Flow<QuoteModel>
    suspend fun addQuote(quoteModel: QuoteModel):Long
    suspend fun deleteQuote(int: Int):Int
    suspend fun getAllQuote():Flow<List<QuoteModel>>
    //suspend fun editQuote(quoteModel: QuoteModel, token:String): Flow<QuoteResponse?>
    //suspend fun addQuote(quoteModel: QuoteModel, token:String): Flow<QuoteResponse?>
}