package com.poran.architecturecomponentsampleapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.poran.architecturecomponentsampleapp.data.api.ApiService
import com.poran.architecturecomponentsampleapp.data.api.SafeApiRequest
import com.poran.architecturecomponentsampleapp.data.db.AppDatabase
import com.poran.architecturecomponentsampleapp.data.db.entities.Quote
import com.poran.architecturecomponentsampleapp.preferences.PreferenceProvider
import com.poran.architecturecomponentsampleapp.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime
import java.time.temporal.ChronoUnit

private const val MINIMUM_INTERVAL=6
class QuoteRepository(private val apiService: ApiService,
                      private val appDatabase:AppDatabase,
                      private val preferences: PreferenceProvider
                                                            ) :SafeApiRequest(){
    private val quotes=MutableLiveData<List<Quote>>()
    init {
        quotes.observeForever {
            saveQuote(it)
        }

    }

    private suspend fun fetchQuotes(){
        if(preferences.getSaveAt()==null||isFetchNeeded(LocalDateTime.parse(preferences.getSaveAt()))){
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
    private fun isFetchNeeded(parse: LocalDateTime):Boolean{
        return  ChronoUnit.HOURS.between(parse,LocalDateTime.now())>MINIMUM_INTERVAL
    }
     private fun saveQuote(quotes: List<Quote>){
        Coroutines.oi {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                preferences.saveQuoteAt(LocalDateTime.now().toString())
            }
            appDatabase.quoteDao().saveAllQuotes(quotes)
        }

    }
}