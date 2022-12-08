package com.example.quote.presentation.recycler.adapter

import com.example.quote.domain.model.QuoteModel

interface QuoteClickListener {
    fun onClick(quoteModel: QuoteModel)
}