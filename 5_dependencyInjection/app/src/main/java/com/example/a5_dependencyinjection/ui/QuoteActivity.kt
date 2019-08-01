package com.example.a5_dependencyinjection.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.a5_dependencyinjection.R
import com.example.a5_dependencyinjection.data.model.Quote
import kotlinx.android.synthetic.main.activity_quote.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContainer
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class QuoteActivity : AppCompatActivity() , KodeinAware {

    override val kodein by closestKodein()
    private val viewModeFactory : QuotesViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)

        initializeUI()
    }

    private fun initializeUI() {
        val viewModel = ViewModelProviders.of(this,viewModeFactory)
            .get(QuoteViewModel::class.java)
        viewModel.getQuotes().observe(this, Observer { quotes->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
                textView_quotes.text = stringBuilder.toString()
            }
        })

        button_add_quote.setOnClickListener {
            val quote = Quote(editText_quote.text.toString() , editText_author.text.toString())
            viewModel.add(quote)
            editText_author.setText("")
            editText_quote.setText("")
        }
    }
}
