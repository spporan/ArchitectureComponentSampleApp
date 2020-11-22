package com.poran.architecturecomponentsampleapp.data.api.respones

import com.poran.architecturecomponentsampleapp.data.db.entities.Quote

data class QuoteResponse  (
    val isSuccessful:Boolean,
    val quotes:List<Quote>
)