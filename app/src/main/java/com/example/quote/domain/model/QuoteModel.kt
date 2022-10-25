package com.example.quote.domain.model

data class QuoteModel(
    var id: Int,
    var quote: String,
    val author: String
)