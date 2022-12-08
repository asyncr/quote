package com.example.quote.presentation.viewmodel

/*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.quote.domain.model.QuoteModel
import com.example.quote.domain.usecase.DeleteQuoteUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class QuoteDeleteFragmentByIDViewModelTest{
    private lateinit var quoteViewModel: DeleteQuoteByIDViewModel

    @RelaxedMockK
    private lateinit var deleteQuoteUseCase: DeleteQuoteUseCase

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        this.quoteViewModel = DeleteQuoteByIDViewModel(
            deleteQuoteUseCase,
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    val quoteAustin = QuoteModel(
        2,
        "La simplicidad es alma de la eficiencia",
        "Austin Freeman"
    )
    val id = quoteAustin.id

    @Test
    fun `when deleteQuoteUseCase is called, then return ID`() = runTest {
        // Given
        coEvery { deleteQuoteUseCase.deleteQuoteById(id) } returns id
        // When
        quoteViewModel.deleteQuoteById(id)
        // Then
        assert(quoteViewModel.deletedId.value == id)
    }

}
 */