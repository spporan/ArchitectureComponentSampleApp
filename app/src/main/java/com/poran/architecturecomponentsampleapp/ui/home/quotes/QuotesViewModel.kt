package com.poran.architecturecomponentsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.poran.architecturecomponentsampleapp.data.repository.QuoteRepository
import com.poran.architecturecomponentsampleapp.utils.lazyDeferred

class QuotesViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {
    val quote by lazyDeferred {
        quoteRepository.getQuotes()
    }

}