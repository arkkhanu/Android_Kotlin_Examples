package com.example.a5_dependencyinjection.data.repository

import androidx.lifecycle.LiveData
import com.example.a5_dependencyinjection.data.db.QuoteDao
import com.example.a5_dependencyinjection.data.model.Quote

class QuoteRepositoryImpl (private  val  quoteDao: QuoteDao): QuoteRepository {
    override fun addQuote(quote: Quote) {
        quoteDao.addQuote(quote)
    }

    override fun getQuote(): LiveData<List<Quote>> = quoteDao.getQuote()
}