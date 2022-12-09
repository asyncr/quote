package com.example.quote.presentation.recycler.adapter

import android.view.animation.AnimationUtils.loadAnimation
import androidx.recyclerview.widget.RecyclerView
import com.example.quote.R
import com.example.quote.databinding.QuoteItemBinding
import com.example.quote.domain.model.QuoteModel
import com.example.quote.presentation.ui.utils.recycler.adapter.QuoteEditClickListener
import com.example.quote.presentation.ui.utils.recycler.adapter.QuoteDeleteClickListener

class QuoteViewHolder(
    private val binding: QuoteItemBinding,
    private val editQuote: QuoteEditClickListener,
    private val deleteQuote: QuoteDeleteClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun findQuote(quote: QuoteModel) {
        with(binding) {
            val cont = root.context
            var transition = loadAnimation(cont, R.anim.fade_transition_animation)
            var scale = loadAnimation(cont, R.anim.fade_scale_anim)
            tvQuote.apply {
                text = quote.quote
                animation = transition
            }
            tvAuthor.apply {
                text = quote.author
                animation = transition
            }
            cardQuote.apply {
                cardQuote.animation = scale
            }
            imgDelete.setOnClickListener {
                deleteQuote.onClickDelete(quote.id)
            }
            imgEdit.setOnClickListener {
                editQuote.onClickEdit(quote)
            }
        }
    }
}