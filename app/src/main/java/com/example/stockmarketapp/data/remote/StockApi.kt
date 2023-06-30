package com.example.stockmarketapp.data.remote

import com.example.stockmarketapp.data.remote.dto.CompanyDetailDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
    companion object{
        const val BASE_URL = "https://alphavantage.co/"
        const val API_KEY = "AHU68E47NTGYQF1B"
    }

    @GET("query?function=LISTING_STATUS")
    suspend fun getCompanies(@Query("apikey") apikey: String = API_KEY) : ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyDetail(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): CompanyDetailDto

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody
}