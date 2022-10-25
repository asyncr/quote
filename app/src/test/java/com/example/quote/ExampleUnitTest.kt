package com.example.quote

import com.example.quote.domain.model.QuoteModel
import dagger.hilt.android.AndroidEntryPoint
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@AndroidEntryPoint
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    //Test of the app if it is working

    val quoteItemModel = QuoteModel(0, "Test QuoteItem", "Test Author")

    @Test
    fun testQuoteModel() {
        assertEquals(quoteItemModel.quote, "Test QuoteItem")
        assertEquals(quoteItemModel.author, "Test Author")
    }

}