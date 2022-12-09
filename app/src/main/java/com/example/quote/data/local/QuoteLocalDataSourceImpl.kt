package com.example.quote.data.local

import com.example.quote.core.utils.toEntity
import com.example.quote.core.utils.toListQuoteModel
import com.example.quote.core.utils.toQuoteModel
import com.example.quote.data.local.daos.QuoteDao
import com.example.quote.data.remote.QuoteApiResponse
import com.example.quote.domain.model.QuoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class QuoteLocalDataSourceImpl @Inject constructor(private val quoteDao: QuoteDao) :
    QuoteLocalDataSource {
    override suspend fun getQuotes(): Flow<QuoteApiResponse> {
        val quotesEntity = quoteDao.getQuotes()
        val data = quotesEntity.map { it.toListQuoteModel() }
        val quotesModel = data.first()
        return flow { emit(QuoteApiResponse(true, "list quotes", quotesModel)) }
    }

    override fun getQuote(quoteId: Int): Flow<QuoteModel?> {
        return quoteDao.getQuote(quoteId).map { it?.toQuoteModel() }
    }

    override fun getQuoteRandom(): Flow<QuoteModel> {
        return quoteDao.getQuoteRandom().map { it.toQuoteModel() }
    }

    override suspend fun insertAll(quotes: List<QuoteModel>) {
        quoteDao.insertAll(quotes.map { it.toEntity() })
    }

    override suspend fun insert(quoteModel: QuoteModel) {
        quoteDao.insert(quoteModel.toEntity())
    }

    override suspend fun editQuote(quoteModel: QuoteModel) {
        quoteDao.updateQuote(quoteModel.toEntity())
    }

    override suspend fun deleteQuote(quoteId: Int):Int {
        return quoteDao.delete(quoteId)
    }
}