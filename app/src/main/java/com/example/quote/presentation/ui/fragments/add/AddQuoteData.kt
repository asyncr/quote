package com.example.quote.presentation.ui.fragments.add

import com.example.quote.presentation.ui.fragments.add.delegation.QuoteAuthorDelegation
import com.example.quote.presentation.ui.fragments.add.delegation.QuoteDelegation
import com.example.quote.presentation.ui.fragments.add.delegation.QuoteidDelegation

class AddQuoteData{
    var id: Int? by QuoteidDelegation()
    var quote: String? by QuoteDelegation()
    var author: String? by QuoteAuthorDelegation()

    constructor( id: Int? = null, quote: String? = null, author: String? = null){
        this.id = id
        this.quote = quote
        this.author = author
    }

    fun isValid() = id != null && quote != null && author != null
}