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
class QuoteViewModelTest {
    private lateinit var quoteViewModel: QuoteViewModel

    @RelaxedMockK
    private lateinit var getQuoteRandomUseCase: GetQuoteRandomUseCase

    @RelaxedMockK
    private lateinit var addQuoteUseCase: AddQuoteUseCase

    @RelaxedMockK
    private lateinit var getAllQuoteUseCase: GetAllQuoteUseCase

    @RelaxedMockK
    private lateinit var deleteQuoteUseCase: DeleteQuoteUseCase

    @RelaxedMockK
    private lateinit var getLatestIdUseCase: GetLatestIdUseCase


    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        this.quoteViewModel = QuoteViewModel(
            getQuoteRandomUseCase,
            addQuoteUseCase,
            getAllQuoteUseCase,
            deleteQuoteUseCase,
            getLatestIdUseCase
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
    fun `when addQuoteUseCase is called, then return 1 if success`() = runTest {
        // Given
        coEvery { addQuoteUseCase.addQuote(quoteDan) } returns 1L
        // When
        quoteViewModel.addQuote(quoteDan)
        // Then
        assert(quoteViewModel.quoteModelAddRowId.value == 1L)
    }

    @Test
    fun `when getAllQuoteUseCase is called, then return list of QuoteModel`() = runTest {
        // Given
        coEvery { getAllQuoteUseCase.getAllQuote() } returns listQuoteModel
        // When
        quoteViewModel.getAllQuote()
        // Then
        assert(quoteViewModel.quoteModelList.value == listQuoteModel.first())
    }

    @Test
    fun `when deleteQuoteUseCase is called, then return 1 if success`() = runTest {
        // Given
        coEvery { deleteQuoteUseCase.deleteQuote(1) } returns 1
        // When
        quoteViewModel.deleteQuote(1)
        // Then
        assert(quoteViewModel.quoteModelDeleteRowId.value == 1)
    }

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