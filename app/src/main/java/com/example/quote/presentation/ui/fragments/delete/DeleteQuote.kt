package com.example.quote.presentation.ui.fragments.delete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quote.R
import com.example.quote.databinding.FragmentDeleteQuoteBinding
import com.example.quote.databinding.FragmentHomeBinding


class DeleteQuote : Fragment() {
    private var _binding: FragmentDeleteQuoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDeleteQuoteBinding.inflate(inflater, container, false)
        return binding.root
    }

}