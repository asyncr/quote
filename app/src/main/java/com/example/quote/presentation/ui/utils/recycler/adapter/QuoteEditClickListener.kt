package com.example.quote.presentation.ui.utils.recycler.adapter

import com.example.quote.domain.model.QuoteModel

interface QuoteEditClickListener {
    fun onClickEdit(quoteModel: QuoteModel)
}