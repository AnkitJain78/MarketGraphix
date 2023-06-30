package com.example.stockmarketapp.data.csv

import java.io.InputStream

interface CSVParser<T> {
    suspend fun parse(input: InputStream): List<T>
}