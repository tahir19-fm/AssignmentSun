package com.hoho.assignmentsun.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hoho.assignmentsun.ui.theme.rainbowColors

@Composable
fun DurationRecommendationCard(
    time: MutableState<String> ?= null,
    constTime: String? = null,
    tool: String,
    title: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = time?.value ?: constTime ?: "",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Divider(
            modifier = Modifier
                .width(100.dp)
                .background(Brush.horizontalGradient(rainbowColors)),
            thickness = 4.dp
        )
        Text(
            text = tool,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White
            )
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White
            )
        )
    }
}