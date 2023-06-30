package com.example.stockmarketapp.presentation.company_list

import com.example.stockmarketapp.domain.model.CompanyModel

data class CompanyListState(
    val companies: List<CompanyModel> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchKeyword: String = ""
)
