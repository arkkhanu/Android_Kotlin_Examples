package com.example.a5_dependencyinjection

import android.app.Application
import com.example.a5_dependencyinjection.data.db.Database
import com.example.a5_dependencyinjection.data.db.DatabaseFakeImpl
import com.example.a5_dependencyinjection.data.db.QuoteDao
import com.example.a5_dependencyinjection.data.db.QuoteDaoFakeImp
import com.example.a5_dependencyinjection.data.repository.QuoteRepository
import com.example.a5_dependencyinjection.data.repository.QuoteRepositoryImpl
import com.example.a5_dependencyinjection.ui.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class QuotesApplication : Application() , KodeinAware {
    override val kodein = Kodein.lazy {
        bind<Database>() with singleton { DatabaseFakeImpl() }
        bind<QuoteDao>() with singleton { instance<Database>().quoteDao }
        bind<QuoteRepository>() with singleton { QuoteRepositoryImpl(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }

    }
}