package com.example.quote.presentation.ui.fragments.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quote.databinding.FragmentQuoteEditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuoteEditFragment : Fragment() {
    private var _binding: FragmentQuoteEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuoteEditBinding.inflate(inflater, container, false)
        with(binding) {
            return root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}