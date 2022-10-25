package com.example.quote.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.quote.domain.model.QuoteModel
import com.example.quote.domain.usecase.*
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteRandomViewModelTest {
    private lateinit var quoteViewModel: QuoteRandomViewModel

    @RelaxedMockK
    private lateinit var getQuoteRandomUseCase: GetQuoteRandomUseCase

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        this.quoteViewModel = QuoteRandomViewModel(
            getQuoteRandomUseCase,
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    val quoteDan = QuoteModel(
        0,
        "A veces vale la pena quedarse en la cama el lunes, en lugar de pasar el resto de la semana depurando el código del lunes",
        "Dan Salomon"
    )

    val quoteDanMutableStateFlow = MutableStateFlow(quoteDan)

    @Test
    fun `when getQuoteRandomUseCase is called, then return QuoteModel`() = runTest {
        // Given
        coEvery { getQuoteRandomUseCase.getQuoteRandom() } returns quoteDanMutableStateFlow
        // When
        quoteViewModel.randomQuote()
        // Then
        assert(quoteViewModel.quoteModel.value == quoteDan)
    }

}