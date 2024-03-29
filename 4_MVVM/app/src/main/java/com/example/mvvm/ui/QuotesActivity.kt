package com.example.mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm.R
import com.example.mvvm.data.Quote
import com.example.mvvm.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_quotes.*

class QuotesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        initailizeUI()
    }

    private fun initailizeUI() {
        val factory = InjectorUtils.provideQuotesViewModelFactor()
        val viewModel = ViewModelProviders.of(this,factory)
            .get(QuotesViewModel::class.java)
        viewModel.getQuotes().observe(this, Observer { quotes->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            textView_quotes.text = stringBuilder.toString()
        })
        button_add_quote.setOnClickListener{
            val quote = Quote(editText_quote.text.toString(),editText_author.text.toString())
            viewModel.addQuotes(quote)
            editText_author.setText("")
            editText_quote.setText("")
        }
    }
}
