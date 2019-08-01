package com.example.mvvm.utilities

import com.example.mvvm.data.FakeDatabase
import com.example.mvvm.data.QuoteRepository
import com.example.mvvm.ui.QuotesViewModelFactory

object InjectorUtils {
    fun provideQuotesViewModelFactor():QuotesViewModelFactory{
        val quoteRepository = QuoteRepository.getIstance(FakeDatabase.getIstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}