package com.poran.architecturecomponentsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.poran.architecturecomponentsampleapp.data.repository.QuoteRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class QuoteViewModelFactory(private val quoteRepository: QuoteRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuotesViewModel::class.java))
            return QuotesViewModel(quoteRepository) as T
        throw IllegalArgumentException()
    }

}