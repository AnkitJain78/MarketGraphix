package com.example.stockmarketapp.presentation.company_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.stockmarketapp.presentation.navigation.Screen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CompanyListScreen(modifier: Modifier = Modifier,
                      navController: NavController,
                      viewModel: CompanyListViewModel = hiltViewModel()
){
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val state = viewModel.state
    Column(modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)) {
        OutlinedTextField(
            value = state.searchKeyword,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onBackground
            ),
            onValueChange = {
                viewModel.onEvent(
                    CompanyListEvent.OnSearchQueryChange(it)
                )
            },
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true
        )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(CompanyListEvent.Refresh)
            }
        ) {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                items(state.companies.size) { i ->
                    val company = state.companies[i]
                    CompanyItem(
                        item = company,
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.DetailScreen.route + "/${company.symbol}"
                                )
                            }
                            .padding(16.dp)
                    )
                    if(i < state.companies.size) {
                        Divider(modifier = modifier.padding(
                            horizontal = 16.dp
                        ),
                        color = Color.Gray)
                    }
                }
            }
        }
    }
}
