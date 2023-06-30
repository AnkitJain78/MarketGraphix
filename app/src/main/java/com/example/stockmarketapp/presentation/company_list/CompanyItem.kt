package com.example.stockmarketapp.presentation.company_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.stockmarketapp.domain.model.CompanyModel
import com.example.stockmarketapp.ui.theme.White
import com.example.stockmarketapp.ui.theme.sourceSans

@Composable
fun CompanyItem(modifier: Modifier = Modifier,
item: CompanyModel) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier) {
            Text(text = item.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = sourceSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = modifier.weight(1f),
                color = MaterialTheme.colors.onBackground
            )

            Text(text = item.exchange,
                fontFamily = sourceSans,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.onBackground
            )
        }
        Text(text = item.symbol,
            fontFamily = sourceSans,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colors.onBackground
        )
    }
}