package com.example.quote.presentation.ui.fragments.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.quote.databinding.FragmentQuoteEditBinding
import com.example.quote.domain.model.QuoteModel
import com.example.quote.presentation.ui.utils.Messages
import com.example.quote.presentation.ui.utils.Utils
import com.example.quote.presentation.viewmodel.QuoteEditViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuoteEditFragment : Fragment() {
    private var _binding: FragmentQuoteEditBinding? = null
    private val binding get() = _binding!!
    private var quoteEditVM: QuoteEditViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuoteEditBinding.inflate(inflater, container, false)
        with(binding) {
            edtId.requestFocus()
            showData()
            btnAdd.setOnClickListener { process() }
            return root
        }
    }

    private fun showData() {
        with(binding) {
            edtId.setText(arguments?.getInt("id", 0).toString())
            edtQuote.setText(arguments?.getString("quote", "").toString())
            edtAuthor.setText(arguments?.getString("author", "").toString())
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
        lifecycleScope.launch(Dispatchers.IO) {
            quoteEditVM!!.editQuote(quoteModel)
        }
    }

    private fun createQuote(): QuoteModel = with(binding) {
        val id = edtId.text.toString().toInt()
        val quote = Utils.removeStrangeCharacters(edtQuote.text.toString())
        val author = Utils.removeStrangeCharacters(edtAuthor.text.toString())
        return QuoteModel(id, quote, author)
    }

    private fun showMessage(msg: String) = Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}