package com.example.stockmarketapp.data.mapper

import com.example.stockmarketapp.data.local.CompanyEntity
import com.example.stockmarketapp.domain.model.CompanyModel

fun CompanyEntity.toCompanyModel(): CompanyModel{
    return CompanyModel(
        this.name,
        this.symbol,
        this.exchange
    )
}

fun CompanyModel.toCompanyEntity(): CompanyEntity{
    return CompanyEntity(
        name = this.name,
        symbol = this.symbol,
        exchange = this.exchange
    )
}