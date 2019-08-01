package com.example.a5_dependencyinjection.data.db

import androidx.lifecycle.LiveData
import com.example.a5_dependencyinjection.data.model.Quote

interface QuoteDao {
    fun addQuote(quote: Quote)
    fun getQuote(): LiveData<List<Quote>>
}