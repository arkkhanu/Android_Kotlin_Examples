package com.example.mvvm.ui

import androidx.lifecycle.ViewModel
import com.example.mvvm.data.Quote
import com.example.mvvm.data.QuoteRepository

class QuotesViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {
    fun getQuotes() = quoteRepository.getQuote()
    fun addQuotes(quote: Quote) = quoteRepository.addQuote(quote)
}