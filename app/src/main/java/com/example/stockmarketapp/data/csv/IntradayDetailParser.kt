package com.example.stockmarketapp.data.csv

import com.example.stockmarketapp.data.mapper.toIntraDetail
import com.example.stockmarketapp.data.remote.dto.IntradayDetailDto
import com.example.stockmarketapp.domain.model.CompanyModel
import com.example.stockmarketapp.domain.model.IntradayDetail
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import javax.inject.Inject

class IntradayDetailParser @Inject constructor() : CSVParser<IntradayDetail> {
    override suspend fun parse(input: InputStream): List<IntradayDetail> {
        val csvReader = CSVReader(InputStreamReader(input))
        return withContext(Dispatchers.IO){
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull {
                    val timestamp = it.getOrNull(0) ?: return@mapNotNull null
                    val close = it.getOrNull(4) ?: return@mapNotNull null
                    val intradayDto = IntradayDetailDto(timestamp, close.toDouble())
                    intradayDto.toIntraDetail()
                }
                .filter {
                    it.timestamp.dayOfMonth == LocalDate.now().minusDays(4).dayOfMonth
                }
                .sortedBy {
                    it.timestamp.hour
                }
                .also {
                    csvReader.close()
                }
        }
    }
}