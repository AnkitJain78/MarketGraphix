package com.example.stockmarketapp.data.local

import androidx.activity.ComponentActivity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyList(companyList: List<CompanyEntity>)

    @Query("DELETE FROM company_entity")
    suspend fun clearCompanyList()

    @Query("SELECT * FROM company_entity WHERE LOWER(name) LIKE '%' || LOWER(:keyword) || '%' " +
            "OR UPPER(:keyword) == symbol")
    suspend fun searchCompanyList(keyword: String): List<CompanyEntity>
}