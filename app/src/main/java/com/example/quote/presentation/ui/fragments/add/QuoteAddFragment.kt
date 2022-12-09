package com.example.quote.presentation.ui.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quote.databinding.FragmentQuoteAddBinding
import com.example.quote.domain.model.QuoteModel
import com.example.quote.presentation.ui.utils.Messages
import com.example.quote.presentation.ui.utils.Utils
import com.example.quote.presentation.viewmodel.QuoteAddViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuoteAddFragment : Fragment() {
    private var _binding: FragmentQuoteAddBinding? = null
    private val binding get() = _binding!!
    private val quoteAddVM: QuoteAddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuoteAddBinding.inflate(inflater, container, false)
        with(binding) {
            edtId.requestFocus()
            btnAdd.setOnClickListener { process() }
            return binding.root
        }
    }

    private fun process() {
        with(binding) {
            when {
                Utils.isValidNumberField(edtId).not() -> {
                    showMessage(Messages.INVALID_ID.message)
                    return@process
                }
                Utils.isValidField(edtQuote).not() -> {
                    showMessage(Messages.INVALID_QUOTE.message)
                    return@process
                }
                Utils.isValidField(edtAuthor).not() -> {
                    showMessage(Messages.INVALID_AUTHOR.message)
                    return@process
                }
                else -> save()
            }
        }
    }

    private fun save() {
        val quoteModel = createQuote()
        val data = Utils.quoteToString(quoteModel)
        val confirm = Utils.showAlert(requireContext(), Messages.CONFIRM_SAVE.message, data)
        confirm.setButton(AlertDialog.BUTTON_POSITIVE, "Si") { dialog, _ ->
            saveQuote(quoteModel)
            dialog.dismiss()
            //showMessage(Messages.QUOTE_SAVED.message)
            cleanFields()
        }
        confirm.setButton(AlertDialog.BUTTON_NEGATIVE, "No") { dialog, _ -> dialog.dismiss() }
        confirm.show()
    }

    private fun createQuote(): QuoteModel = with(binding) {
        val id = edtId.text.toString().toInt()
        val quote = Utils.removeStrangeCharacters(edtQuote.text.toString())
        val author = Utils.removeStrangeCharacters(edtAuthor.text.toString())
        return QuoteModel(id, quote, author)
    }

    private fun saveQuote(quoteModel: QuoteModel) {
        lifecycleScope.launch(Dispatchers.IO) {
            quoteAddVM.addQuote(quoteModel)
        }
    }

    private fun showMessage(msg: String) = makeText(context, msg, Toast.LENGTH_SHORT).show()

    private fun cleanFields() = with(binding) {
        edtId.setText("")
        edtQuote.setText("")
        edtAuthor.setText("")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}