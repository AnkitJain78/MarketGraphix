package com.example.stockmarketapp.di

import android.content.Context
import com.example.stockmarketapp.data.csv.CSVParser
import com.example.stockmarketapp.data.csv.CSVParserImpl
import com.example.stockmarketapp.data.csv.IntradayDetailParser
import com.example.stockmarketapp.data.local.CompanyEntity
import com.example.stockmarketapp.data.repository.StockRepositoryImpl
import com.example.stockmarketapp.domain.model.CompanyDetail
import com.example.stockmarketapp.domain.model.CompanyModel
import com.example.stockmarketapp.domain.model.IntradayDetail
import com.example.stockmarketapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CSVParserImpl
    ): CSVParser<CompanyModel>

    @Binds
    @Singleton
    abstract fun bindIntradayDetailParser(
        intradayDetailParser: IntradayDetailParser
    ): CSVParser<IntradayDetail>
}