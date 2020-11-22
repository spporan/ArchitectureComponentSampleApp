package com.poran.architecturecomponentsampleapp.data.db

import androidx.room.Insert
import androidx.room.Query
import com.poran.architecturecomponentsampleapp.data.db.entities.Quote

interface QuoteDao {
    @Insert
    fun saveAllQuotes(quotes:List<Quote>)

    @Query("SELECT * FROM Quote")
    fun getQuotes():List<Quote>
}