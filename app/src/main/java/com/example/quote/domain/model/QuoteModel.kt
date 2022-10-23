package com.example.quote.domain.model

import android.os.Parcelable

data class QuoteModel(
    var id: Int,
    var quote: String,
    val author: String
)