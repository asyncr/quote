package com.example.quote.presentation.recycler.adapter

import android.view.animation.AnimationUtils.loadAnimation
import androidx.recyclerview.widget.RecyclerView
import com.example.quote.R
import com.example.quote.databinding.QuoteItemBinding
import com.example.quote.domain.model.QuoteModel

class QuoteViewHolder(
    private val binding: QuoteItemBinding,
    private val quoteClickListener: QuoteClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun findQuote(quote: QuoteModel) {
        with(binding) {
            var transition = loadAnimation(binding.root.context, R.anim.fade_transition_animation)
            var scale = loadAnimation(binding.root.context, R.anim.fade_scale_anim)
            tvQuote.apply {
                text = quote.quote
                animation = transition
            }
            tvAuthor.apply {
                text = quote.author
                animation = transition
            }
            cardQuote.apply {
                setOnClickListener { quoteClickListener.onClick(quote) }
                cardQuote.animation = scale
            }
        }
    }
}