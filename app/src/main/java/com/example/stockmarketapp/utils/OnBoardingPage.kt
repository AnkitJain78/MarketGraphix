package com.example.stockmarketapp.utils

import androidx.annotation.DrawableRes
import com.example.stockmarketapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
){
    object First : OnBoardingPage(
        image = R.drawable.screen1,
        title = "Get Our Best Recommendation",
        description = "Unlock your personalized recommendations and empower yourself to make confident decisions.\n" +
                "\n"
    )

    object Second : OnBoardingPage(
        image = R.drawable.screen3,
        title = "Hear The Expert",
        description = "Gain access to exclusive expert analysis and commentary on market trends"
    )

    object Third : OnBoardingPage(
        image = R.drawable.screen2,
        title = "When To Buy",
        description = "Get ready to seize the right opportunities at the right time"
    )
}
