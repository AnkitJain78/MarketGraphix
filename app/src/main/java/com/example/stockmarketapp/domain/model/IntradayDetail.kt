package com.example.stockmarketapp.domain.model

import java.time.LocalDateTime

data class IntradayDetail(
    val timestamp: LocalDateTime,
    val close: Double
)
