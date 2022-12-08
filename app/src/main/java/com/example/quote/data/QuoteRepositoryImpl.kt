package com.example.quote.data

import com.example.quote.data.local.QuoteLocalDataSource
import com.example.quote.data.remote.QuoteApiResponse
import com.example.quote.data.remote.QuoteRemoteDataSource
import com.example.quote.domain.QuoteRepository
import com.example.quote.domain.model.QuoteModel
import com.example.quote.presentation.NetworkErrorException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepositoryImpl @Inject constructor
    (private val localDataSource: QuoteLocalDataSource,
     private  val remoteDataSource: QuoteRemoteDataSource,
):
    QuoteRepository {

    override suspend fun getQuoteRandom(): Flow<QuoteModel> {
        return  localDataSource.getQuoteRandom()
    }

    override suspend fun getQuote(quoteId: Int): Flow<QuoteModel> {
        return localDataSource.getQuote(quoteId)
    }
/*
    override suspend fun getQuotes(): Flow<QuoteResponse?> {
        return  localDataSource.getQuotes()
    }

    */

    override suspend fun getQuotes(token: String): Flow<QuoteApiResponse?> {
        val localQuotes=  localDataSource.getQuotes()
        val  remoteQuotes =
            try {
                remoteDataSource.getQuotes(token)
            } catch (ex: Exception) {
                when (ex) {
                    is NetworkErrorException -> throw ex
                    else -> null
                }
            }

        if (remoteQuotes != null) {
            localDataSource.insertAll(remoteQuotes.first().data as List<QuoteModel>)
        }
        return (remoteQuotes ?: localQuotes)
    }

    override suspend fun editQuote(quoteModel: QuoteModel) {
        return localDataSource.editQuote(quoteModel)
    }

    override suspend fun addQuote(quoteModel: QuoteModel) {
        return localDataSource.insert(quoteModel)
    }
}