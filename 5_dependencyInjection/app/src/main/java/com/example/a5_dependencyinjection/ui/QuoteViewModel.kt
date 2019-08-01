package com.example.a5_dependencyinjection.ui

import androidx.lifecycle.ViewModel
import com.example.a5_dependencyinjection.data.model.Quote
import com.example.a5_dependencyinjection.data.repository.QuoteRepository

class QuoteViewModel(private  val quoteRepository: QuoteRepository) : ViewModel() {
    fun add(quote : Quote) = quoteRepository.addQuote(quote)
    fun getQuotes() = quoteRepository.getQuote()
}