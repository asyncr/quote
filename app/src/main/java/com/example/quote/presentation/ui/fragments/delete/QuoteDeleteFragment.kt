package com.example.quote.presentation.ui.fragments.delete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quote.databinding.FragmentQuoteDeleteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuoteDeleteFragment : Fragment() {
    private var _binding: FragmentQuoteDeleteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuoteDeleteBinding.inflate(inflater, container, false)
        with(binding) {
            return root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}