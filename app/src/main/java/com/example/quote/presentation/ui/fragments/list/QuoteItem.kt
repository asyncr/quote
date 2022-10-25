package com.example.quote.presentation.ui.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quote.R
import com.example.quote.databinding.FragmentQuoteBinding


class QuoteItem : Fragment() {
    private var _binding: FragmentQuoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuoteBinding.inflate(inflater, container, false)
        val bundle = arguments
        val id = bundle?.getInt("id")
        val quote = bundle?.getString("quote")
        val author = bundle?.getString("author")
        with(binding) {
            tvQuote.text = quote
            tvAuthor.text = author
            btnBack.setOnClickListener { replacementFragment() }
        }
        return binding.root
    }

    private fun replacementFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.host_fragment, ListQuote()).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}