package com.example.stockmarketapp.presentation.company_details

import com.example.stockmarketapp.domain.model.CompanyDetail
import com.example.stockmarketapp.domain.model.IntradayDetail

data class CompanyDetailState(
    val stockDetails: List<IntradayDetail> = emptyList(),
    val company: CompanyDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)