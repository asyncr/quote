package com.example.quote.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quote.data.local.entities.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Insert
    suspend fun insert(quote: QuoteEntity):Long //Retorna el id del registro insertado

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<QuoteEntity>)

    @Query("SELECT * FROM quote ORDER BY author ASC")
    fun getQuotes(): Flow<List<QuoteEntity>>

    @Query("SELECT * FROM quote WHERE id = :quoteId")
    fun getQuote(quoteId: Int): Flow<QuoteEntity>

    @Query("SELECT * FROM quote ORDER BY random() LIMIT 1")
    fun getQuoteRandom(): Flow<QuoteEntity>

    @Query("DELETE FROM quote")
    suspend fun deleteAll()

    @Query("DELETE FROM quote WHERE id=:quoteId ")
    suspend fun delete(quoteId: Int): Int //Retorna el número de registros eliminados

    @Query("SELECT MAX(id) FROM quote")
    suspend fun getLatestId(): Int
}