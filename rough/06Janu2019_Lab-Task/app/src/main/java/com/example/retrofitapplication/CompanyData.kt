package com.example.retrofitapplication

import java.io.Serializable

class CompanyData : Serializable {

    val item: Array<CompanyItems>? = null

    class CompanyItems {

        val id: String? = null
        val type: String? = null
        val url: String? = null
        val created_at: String? = null
        val company: String? = null
        val company_url: String? = null
        val location: String? = null
        val title: String? = null
        val description: String? = null
        val how_to_apply: String? = null
        val company_logo: String? = null

    }
}