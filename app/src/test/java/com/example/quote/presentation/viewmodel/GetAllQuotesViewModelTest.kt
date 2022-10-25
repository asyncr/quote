package com.example.quote.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.quote.domain.model.QuoteModel
import com.example.quote.domain.usecase.GetAllQuoteUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetAllQuotesViewModelTest{
    private lateinit var quoteViewModel: GetAllQuotesViewModel
    @RelaxedMockK
    private lateinit var getAllQuoteUseCase: GetAllQuoteUseCase

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        this.quoteViewModel = GetAllQuotesViewModel(
            getAllQuoteUseCase,
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    val quoteDan = QuoteModel(
        0,
        "A veces vale la pena quedarse en la cama el lunes, en lugar de pasar el resto de la semana depurando el código del lunes",
        "Dan Salomon"
    )

    val quoteCory = QuoteModel(
        1,
        "El código es como una broma. Si tienes que explicarlo, no es bueno",
        "Cory House"
    )

    val quoteAustin = QuoteModel(
        2,
        "La simplicidad es alma de la eficiencia",
        "Austin Freeman"
    )

    val listQuoteModel = MutableStateFlow(listOf(quoteDan, quoteCory, quoteAustin))


    @Test
    fun `when getAllQuoteUseCase is called, then return list of QuoteModel`() = runTest {
        // Given
        coEvery { getAllQuoteUseCase.getAllQuote() } returns listQuoteModel
        // When
        quoteViewModel.getAllQuotes()
        // Then
        assert(quoteViewModel.quoteModelList.value == listQuoteModel.first())
    }

}