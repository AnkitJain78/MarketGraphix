package com.example.stockmarketapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.stockmarketapp.R

val sourceSans = FontFamily(
    Font(R.font.source_sans, FontWeight.Normal),
    Font(R.font.source_sans_bold, FontWeight.Bold),
    Font(R.font.source_sans_light, FontWeight.Light),
    Font(R.font.source_sans_extralight, FontWeight.ExtraLight),
    Font(R.font.source_sans_regular, FontWeight.Medium),
    Font(R.font.source_sans_semibold, FontWeight.SemiBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)