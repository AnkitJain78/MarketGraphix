package com.example.stockmarketapp.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.stockmarketapp.data.csv.CSVParser
import com.example.stockmarketapp.data.local.StockDatabase
import com.example.stockmarketapp.data.mapper.toCompanyDetail
import com.example.stockmarketapp.data.mapper.toCompanyEntity
import com.example.stockmarketapp.data.mapper.toCompanyModel
import com.example.stockmarketapp.data.remote.StockApi
import com.example.stockmarketapp.domain.model.CompanyDetail
import com.example.stockmarketapp.domain.model.CompanyModel
import com.example.stockmarketapp.domain.model.IntradayDetail
import com.example.stockmarketapp.domain.repository.StockRepository
import com.example.stockmarketapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

@Singleton
class StockRepositoryImpl @Inject constructor(
    val remoteApi: StockApi,
    val localDB: StockDatabase,
    val companyListParser: CSVParser<CompanyModel>,
    val intradayParser: CSVParser<IntradayDetail>,
    context: Context
): StockRepository {
    val dao = localDB.dao
    override suspend fun getCompanyListing(
        keyword: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<CompanyModel>>> {
        return flow {
            emit(Resource.Loading(true))
            val data = dao.searchCompanyList(keyword)
            emit(Resource.Success(data.map {
                it.toCompanyModel()
            }))
            val isLocalEmpty = data.isEmpty() && keyword.isEmpty()
            val shouldLoadFromLocal = !isLocalEmpty && !fetchFromRemote
            if(shouldLoadFromLocal){
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteData = try {
                companyListParser.parse(remoteApi.getCompanies().byteStream())
            }
            catch (e: Exception){
                e.printStackTrace()
                emit(Resource.Error(message = e.localizedMessage!!))
                null
            }
            Log.d("remotedata", remoteApi.getCompanies().toString())
            remoteData?.let { data ->
                Log.d("remotedata",remoteData.toString())
                dao.clearCompanyList()
                dao.insertCompanyList(data.map {
                    it.toCompanyEntity()
                })
                emit(Resource.Success(dao.searchCompanyList("")
                    .map {
                        it.toCompanyModel()
                    }))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getIntradayDetails(symbol: String): Resource<List<IntradayDetail>> {
        return try {
            val result = remoteApi.getIntradayInfo(symbol)
            val intradayList = intradayParser.parse(result.byteStream())
            Resource.Success(intradayList)
        }
        catch (e: IOException){
            Resource.Error(
                message = e.localizedMessage ?: "Error in loading intraday info"
            )
        }
        catch(e: HttpException) {
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        }
    }

    override suspend fun getCompanyDetails(symbol: String): Resource<CompanyDetail> {
        return try {
            val result = remoteApi.getCompanyDetail(symbol)
            val res = result.toCompanyDetail()
            Resource.Success(res)
        }
        catch (e: IOException){
            Resource.Error(
                message = e.localizedMessage ?: "Error in loading intraday info"
            )
        }
        catch(e: HttpException) {
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        }
    }

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
    }
    private val dataStore = context.dataStore

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

}