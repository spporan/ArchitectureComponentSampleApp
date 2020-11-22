package com.poran.architecturecomponentsampleapp.data.repository

import com.poran.architecturecomponentsampleapp.data.api.ApiService
import com.poran.architecturecomponentsampleapp.data.db.AppDatabase

class QuoteRepository(private val apiService: ApiService,private val appDatabase:AppDatabase) {
}