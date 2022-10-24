package com.example.quote

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quote.domain.QuoteRepository
import com.example.quote.domain.model.QuoteModel
import com.example.quote.presentation.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    val quoteModel = QuoteModel(0, "Test Quote", "Test Author")

    @Test
    fun testQuoteModel() {
        assertEquals(quoteModel.quote, "Test Quote")
        assertEquals(quoteModel.author, "Test Author")
    }

}