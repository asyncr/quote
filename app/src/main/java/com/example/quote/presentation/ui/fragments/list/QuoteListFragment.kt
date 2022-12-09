package com.example.quote.presentation.ui.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quote.R
import com.example.quote.databinding.FragmentQuoteListBinding
import com.example.quote.domain.model.QuoteModel
import com.example.quote.presentation.ui.fragments.edit.QuoteEditFragment
import com.example.quote.presentation.ui.fragments.login.Login
import com.example.quote.presentation.ui.utils.Messages
import com.example.quote.presentation.ui.utils.Utils
import com.example.quote.presentation.ui.utils.recycler.adapter.QuoteAdapter
import com.example.quote.presentation.ui.utils.recycler.adapter.QuoteDeleteClickListener
import com.example.quote.presentation.ui.utils.recycler.adapter.QuoteEditClickListener
import com.example.quote.presentation.viewmodel.DataStoreViewModel
import com.example.quote.presentation.viewmodel.QuoteDeleteViewModel
import com.example.quote.presentation.viewmodel.QuoteListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuoteListFragment : Fragment(), QuoteEditClickListener, QuoteDeleteClickListener {
    private val quoteListViewModel: QuoteListViewModel by viewModels()
    private val dataStoreViewModel: DataStoreViewModel by viewModels()
    private var _binding: FragmentQuoteListBinding? = null
    private val quoteDeleteVM: QuoteDeleteViewModel by viewModels()
    private val binding get() = _binding!!
    private lateinit var adapterRV: QuoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuoteListBinding.inflate(inflater, container, false)
        observer()
        observerDataStore()
        return binding.root
    }

    private fun observerDataStore() {
        dataStoreViewModel.let {
            lifecycleScope.launch(Dispatchers.IO) {
                val token = it.getToken()
                if (token.isEmpty()) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        val alert = Utils.showAlert(
                            requireContext(), "Token",
                            Messages.TOKEN_NOT_FOUND.message
                        )
                        alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { _, _ ->
                            changeFragment(Login())
                        }
                        alert.show()
                        parentFragmentManager.beginTransaction()
                            .remove(this@QuoteListFragment)
                            .commit()
                    }
                } else {
                    quoteListViewModel.getQuotes("Bearer $token")
                }
            }
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
            quoteListViewModel.quotes.collect {
                val list = it.data as List<QuoteModel>
                gridLayoutManager(list)
                filter(list)
            }
        }
    }

    private fun gridLayoutManager(list: List<QuoteModel>) {
        binding.rvQuotes.apply {
            adapterRV = QuoteAdapter(
                list,
                this@QuoteListFragment,
                this@QuoteListFragment
            )
            adapter = adapterRV
            layoutManager = GridLayoutManager(context, 1)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickEdit(quoteModel: QuoteModel) {
        val bundle = bundleOf(
            Pair("id", quoteModel.id),
            Pair("quote", quoteModel.quote),
            Pair("author", quoteModel.author)
        )
        changeFragment(QuoteEditFragment().apply { arguments = bundle })
    }

    private fun changeFragment(frg: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.nav_content, frg)
            .addToBackStack(this.tag)
            .setReorderingAllowed(true)
            .commit()
    }

    override fun onClickDelete(id: Int) {
        lifecycleScope.launch {
            quoteDeleteVM.deleteQuoteById(id)
            Toast.makeText(requireContext(), "Eliminado correctamente", Toast.LENGTH_SHORT).show()
        }
    }

}
