package com.example.quote.presentation.ui.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quote.databinding.FragmentAddQuoteBinding
import com.example.quote.domain.model.QuoteModel
import com.example.quote.presentation.viewmodel.AddQuoteViewModel
import com.example.quote.presentation.viewmodel.QuoteRandomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddQuote : Fragment() {
    private var _binding: FragmentAddQuoteBinding? = null
    private val quoteViewModel: AddQuoteViewModel by viewModels()
    private val binding get() = _binding!!
    private var idQuote = 0
    private var numberRowAffected = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddQuoteBinding.inflate(inflater, container, false)
        binding.edtQoute.requestFocus()
        showTV(false, flagAuthor = false)
        sendData()
        return binding.root
    }

    private fun sendData() {
        with(binding) {
            btnAddQuote.setOnClickListener {
                val (author, quote) = edtAuthor.text.toString() to edtQoute.text.toString()
                val data = AddQuoteData(idQuote, quote, author)
                when {
                    data.isValid() -> {
                        val quoteModel = QuoteModel(idQuote, quote, author)
                        addQuote(quoteModel)
                    }
                    else -> showMessage("Por favor ingrese todos datos")
                }
            }
        }
    }

    private fun addQuote(quoteModel: QuoteModel) {
        insertQuote(quoteModel)
        showMessage(getStatusMessage())
        clearFields()
    }

    private fun clearFields() {
        if (isCorrectInsertData()){
            binding.edtQoute.setText("")
            binding.edtAuthor.setText("")
        }
    }

    private fun showMessage(message: String) = makeText(context, message, LENGTH_SHORT).show()

    private fun showTV(flagQuote: Boolean, flagAuthor: Boolean) {
        binding.tvQuoteE.isEnabled = flagQuote
        binding.tvAuthorE.isEnabled = flagAuthor
    }

    private fun insertQuote(quoteModel: QuoteModel) {
        lifecycleScope.launch {
            quoteViewModel.addQuote(quoteModel)
            numberRowAffected = quoteViewModel.quoteModelAddRowId.value
        }
    }

    private fun isCorrectInsertData() = numberRowAffected != -1L

    private fun getStatusMessage(): String {
        return if(isCorrectInsertData())"Se agrego correctamente" else "Hubo un error al agregar"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}