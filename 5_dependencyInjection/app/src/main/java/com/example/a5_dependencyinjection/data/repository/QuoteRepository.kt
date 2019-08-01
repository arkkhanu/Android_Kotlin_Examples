package com.example.a5_dependencyinjection.data.repository

import androidx.lifecycle.LiveData
import com.example.a5_dependencyinjection.data.db.Database
import com.example.a5_dependencyinjection.data.model.Quote

interface QuoteRepository {
    fun addQuote(quote: Quote)
    fun getQuote() : LiveData<List<Quote>>
}