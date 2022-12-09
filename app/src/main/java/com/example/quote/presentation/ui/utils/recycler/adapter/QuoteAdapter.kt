package com.example.quote.presentation.ui.utils.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quote.databinding.QuoteItemBinding
import com.example.quote.domain.model.QuoteModel
import com.example.quote.presentation.recycler.adapter.QuoteViewHolder

class QuoteAdapter(
    private var list: List<QuoteModel>,
    private val editQuote: QuoteEditClickListener,
    private val deleteQuote: QuoteDeleteClickListener
) : RecyclerView.Adapter<QuoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val layout = LayoutInflater.from(parent.context)
        val binding = QuoteItemBinding.inflate(layout, parent, false)
        return QuoteViewHolder(binding, editQuote, deleteQuote)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.findQuote(list[position])
    }

    override fun getItemCount() = list.size

    fun updateList(list: List<QuoteModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}