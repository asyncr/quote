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
import com.example.quote.R
import com.example.quote.databinding.FragmentQuoteListBinding
import com.example.quote.domain.model.QuoteModel
import com.example.quote.presentation.recycler.adapter.Onclick
import com.example.quote.presentation.recycler.adapter.QuoteAdapter
import com.example.quote.presentation.viewmodel.DataStoreViewModel
import com.example.quote.presentation.viewmodel.QuoteListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuoteListFragment : Fragment() {
    private val quoteViewModel: QuoteListViewModel by viewModels()
    private var _binding: FragmentQuoteListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterRV: QuoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuoteListBinding.inflate(inflater, container, false)
        with(binding) {
            return root
        }
    }

    private fun filter(list: List<QuoteModel>) {
        binding.edtFindQuote.addTextChangedListener { edt ->
            list.filter { quoteModel ->
                quoteModel.quote.contains(edt.toString(), true)
            }.let {
                adapterRV.updateList(it)
            }
        }
    }

    private fun observer() {
        lifecycleScope.launch {
            quoteViewModel.quotes.let { state ->
                with(binding) {

                }
            }
        }
    }

    private fun createAdapter(list: List<QuoteModel>): QuoteAdapter {
        return adapterRV.apply {

        }
    }

    private fun changeFragment(frg: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.nav_content, frg)
            .addToBackStack(this.tag)
            .setReorderingAllowed(true)
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
