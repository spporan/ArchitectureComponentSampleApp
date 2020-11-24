package com.poran.architecturecomponentsampleapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.poran.architecturecomponentsampleapp.data.api.ApiService
import com.poran.architecturecomponentsampleapp.data.api.SafeApiRequest
import com.poran.architecturecomponentsampleapp.data.db.AppDatabase
import com.poran.architecturecomponentsampleapp.data.db.entities.Quote
import com.poran.architecturecomponentsampleapp.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepository(private val apiService: ApiService,private val appDatabase:AppDatabase) :SafeApiRequest(){
    private val quotes=MutableLiveData<List<Quote>>()
    init {
        quotes.observeForever {
            saveQuote(it)
        }

    }

    private suspend fun fetchQuotes(){
        if(isFetchNeeded()){
            val  response=apiRequest { apiService.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }
     suspend fun getQuotes():LiveData<List<Quote>>{
        return  withContext(Dispatchers.IO){
            fetchQuotes()
            appDatabase.quoteDao().getQuotes()
        }
    }
    private fun isFetchNeeded():Boolean{
        return  true
    }
     private fun saveQuote(quotes: List<Quote>){
        Coroutines.oi {
            appDatabase.quoteDao().saveAllQuotes(quotes)
        }

    }
}