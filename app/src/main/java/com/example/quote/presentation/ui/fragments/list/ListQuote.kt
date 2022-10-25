package com.example.quote.presentation.ui.fragments.list

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quote.R
import com.example.quote.databinding.FragmentSearchQuoteBinding
import com.example.quote.domain.model.QuoteModel
import com.example.quote.presentation.recycler.adapter.QuoteAdapter
import com.example.quote.presentation.recycler.adapter.QuoteClickListener
import com.example.quote.presentation.viewmodel.GetAllQuotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListQuote : Fragment(), QuoteClickListener {
    private val quoteViewModel: GetAllQuotesViewModel by viewModels()
    private var _binding: FragmentSearchQuoteBinding? = null
    private val binding get() = _binding!!

    private var animator: ValueAnimator? = null
    private var adapterRV: QuoteAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchQuoteBinding.inflate(inflater, container, false)
        quoteViewModel.getAllQuotes()
        observer()
        with(binding) {
            Animation.animationButton(rvQuotes, tvAddQuote, animator)
        }
        return binding.root
    }

    private fun filter(list: List<QuoteModel>) {
        binding.edtQA.addTextChangedListener { edt ->
            list.filter { quoteModel ->
                quoteModel.quote.contains(edt.toString(), true)
            }.let { adapterRV?.updateList(it) }
        }
    }

    private fun observer() {
        lifecycleScope.launch {
            quoteViewModel.quoteModelList.collect {
                gridLayoutManager(it.shuffled())
                filter(it)
            }
        }
    }

    private fun gridLayoutManager(list: List<QuoteModel>) {
        binding.rvQuotes.apply {
            adapterRV = QuoteAdapter(list, this@ListQuote)
            adapter = adapterRV
            layoutManager = GridLayoutManager(context, 1)
        }
    }

    override fun onClick(quoteModel: QuoteModel) {
        val bundle = Bundle()
        val (id, quote, author) = quoteModel
        bundle.apply {
            putInt("id", id)
            putString("quote", quote)
            putString("author", author)
        }
        val fragment = QuoteItem()
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.host_fragment, fragment).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}