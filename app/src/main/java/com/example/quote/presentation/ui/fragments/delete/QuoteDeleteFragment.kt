package com.example.quote.presentation.ui.fragments.delete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quote.databinding.FragmentQuoteDeleteBinding
import com.example.quote.presentation.ui.utils.Messages
import com.example.quote.presentation.ui.utils.Utils
import com.example.quote.presentation.viewmodel.QuoteDeleteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuoteDeleteFragment : Fragment() {
    private var _binding: FragmentQuoteDeleteBinding? = null
    private val binding get() = _binding!!
    private val quoteDeleteVM: QuoteDeleteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuoteDeleteBinding.inflate(inflater, container, false)
        observer()
        binding.btnDelete.setOnClickListener { this.delete() }
        return binding.root
    }

    private fun delete() {
        with(binding) {
            if (Utils.isValidNumberField(edtId).not()) {
                showMessage(Messages.INVALID_ID.message)
                return@delete
            }
            val id = edtId.text.toString().toInt()
            lifecycleScope.launch {
                quoteDeleteVM.deleteQuoteById(id)
            }
        }
    }

    private fun observer() {
        lifecycleScope.launch {
            quoteDeleteVM.deleteQuote.collect {
                when (it) {
                    1 -> showMessage(Messages.QUOTE_DELETED.message)
                    0 -> showMessage(Messages.QUOTE_NOT_DELETED.message)
                }
            }
        }
    }

    private fun showMessage(msg: String) = makeText(context, msg, Toast.LENGTH_SHORT).show()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}