package com.example.stockmarketapp.domain.repository

import com.example.stockmarketapp.domain.model.CompanyDetail
import com.example.stockmarketapp.domain.model.CompanyModel
import com.example.stockmarketapp.domain.model.IntradayDetail
import com.example.stockmarketapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListing(
        keyword: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<CompanyModel>>>

    suspend fun getIntradayDetails(
        symbol: String
    ): Resource<List<IntradayDetail>>

    suspend fun getCompanyDetails(
        symbol: String
    ): Resource<CompanyDetail>
}