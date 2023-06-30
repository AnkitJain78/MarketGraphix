package com.example.stockmarketapp.data.mapper

import com.example.stockmarketapp.data.remote.dto.IntradayDetailDto
import com.example.stockmarketapp.domain.model.IntradayDetail
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun IntradayDetailDto.toIntraDetail(): IntradayDetail{
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return IntradayDetail(
        timestamp = localDateTime,
        close = close
    )
}