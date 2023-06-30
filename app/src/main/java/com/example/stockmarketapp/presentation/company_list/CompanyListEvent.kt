package com.example.stockmarketapp.presentation.company_list

sealed class CompanyListEvent {
    object Refresh: CompanyListEvent()
    data class OnSearchQueryChange(val keyword: String): CompanyListEvent()
}
