package com.example.quote.presentation.ui.fragments.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quote.databinding.FragmentQuoteRandomBinding
import com.example.quote.presentation.viewmodel.QuoteRandomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentQuoteRandom : Fragment() {
    private var _binding: FragmentQuoteRandomBinding? = null
    private val binding get() = _binding!!
    private val quoteRandomVM: QuoteRandomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuoteRandomBinding.inflate(inflater, container, false)
        observer()
        quoteRandomVM.randomQuote()
        with(binding) {
            viewContainer.setOnClickListener { quoteRandomVM.randomQuote() }
            return root
        }
    }

    private fun observer() {
        lifecycleScope.launch {
            quoteRandomVM.quoteModel.collect {
                binding.tvQuote.text = it.quote
                binding.tvAuthor.text = it.author
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}