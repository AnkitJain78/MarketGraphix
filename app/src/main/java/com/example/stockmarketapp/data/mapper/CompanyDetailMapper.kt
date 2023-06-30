package com.example.stockmarketapp.data.mapper

import com.example.stockmarketapp.data.remote.dto.CompanyDetailDto
import com.example.stockmarketapp.domain.model.CompanyDetail

fun CompanyDetailDto.toCompanyDetail(): CompanyDetail{
    return CompanyDetail(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}