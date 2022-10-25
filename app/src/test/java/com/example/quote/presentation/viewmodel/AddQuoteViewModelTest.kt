package com.example.quote.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.quote.domain.model.QuoteModel
import com.example.quote.domain.usecase.AddQuoteUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AddQuoteViewModelTest {
    private lateinit var quoteViewModel: AddQuoteViewModel

    @RelaxedMockK
    private lateinit var addQuoteUseCase: AddQuoteUseCase

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        this.quoteViewModel = AddQuoteViewModel(
            addQuoteUseCase,
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    val quoteDan = QuoteModel(
        0,
        "A veces vale la pena quedarse en la cama el lunes, en lugar de pasar el resto de la semana depurando el código del lunes",
        "Dan Salomon"
    )

    @Test
    fun `when addQuoteUseCase is called, then return 1 if success`() = runTest {
        // Given
        coEvery { addQuoteUseCase.addQuote(quoteDan) } returns 1L
        // When
        quoteViewModel.addQuote(quoteDan)
        // Then
        assert(quoteViewModel.quoteModelAddRowId.value == 1L)
    }

}