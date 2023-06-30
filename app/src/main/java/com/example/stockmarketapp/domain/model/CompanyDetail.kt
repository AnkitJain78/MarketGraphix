package com.example.stockmarketapp.domain.model

data class CompanyDetail(
   val symbol: String? = "",
   val name: String? = "",
   val country: String? = "",
   val industry: String? = "",
   val description: String? = ""
)