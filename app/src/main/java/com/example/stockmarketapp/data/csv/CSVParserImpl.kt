package com.example.stockmarketapp.data.csv

import com.example.stockmarketapp.domain.model.CompanyModel
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import javax.inject.Inject
import javax.inject.Singleton

class CSVParserImpl @Inject constructor() : CSVParser<CompanyModel> {
    override suspend fun parse(input: InputStream): List<CompanyModel> {
        val csvReader = CSVReader(InputStreamReader(input))
        return withContext(Dispatchers.IO){
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull {
                    val symbol = it.getOrNull(0)
                    val name = it.getOrNull(1)
                    val exchange = it.getOrNull(2)
                    CompanyModel(
                        name = name ?: return@mapNotNull null,
                        symbol = symbol ?: return@mapNotNull null,
                        exchange = exchange ?: return@mapNotNull null
                    )
                }
                .also {
                    csvReader.close()
                }
        }
    }
}