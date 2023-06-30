package com.example.stockmarketapp.presentation.company_list

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarketapp.domain.repository.StockRepository
import com.example.stockmarketapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListViewModel @Inject constructor(
    private val repository: StockRepository
): ViewModel() {
    var state by mutableStateOf(CompanyListState())
    private var searchJob: Job? = null
    init {
        getCompanyList()
    }
    fun onEvent(event: CompanyListEvent){
        when(event){
            is CompanyListEvent.Refresh ->{
                getCompanyList(state.searchKeyword.lowercase(),true)
            }
            is CompanyListEvent.OnSearchQueryChange ->{
                state = state.copy(searchKeyword = event.keyword)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500)
                    getCompanyList(state.searchKeyword, false)
                }
            }
        }
    }

    private fun getCompanyList(
        keyword: String = state.searchKeyword.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch{
            repository.getCompanyListing(
                keyword, fetchFromRemote
            ).collect{ resource ->
                when(resource){
                    is Resource.Loading ->{
                        state = state.copy(isLoading = resource.isLoading)
                    }
                    is Resource.Success ->{
                        resource.data?.let {
                            state = state.copy(companies = it)
                        }
                    }
                    is Resource.Error ->{
                        Unit
                    }
                }
            }
        }

    }
}