package com.hoho.assignmentsun.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hoho.assignmentsun.R
import com.hoho.assignmentsun.model.WeatherDetails
import com.hoho.assignmentsun.ui.theme.BlackTransparent
import com.hoho.assignmentsun.ui.theme.LocalSpacing

@Composable
fun SlotCard(weatherDetails: WeatherDetails) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .padding(spacing.extraSmall)
            .background(
                BlackTransparent,
                shape = RoundedCornerShape(spacing.medium)
            )
            .width(180.dp),
    ) {
        Column(
            modifier = Modifier.padding(spacing.small)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.extraSmall)
            ) {
                Image(
                    painter = painterResource(id = weatherDetails.weatherIcon),
                    contentDescription = stringResource(id = R.string.weather_icon),
                    modifier = Modifier.size(spacing.extraLarge)
                )
                Column(
                    modifier = Modifier.padding(start = spacing.small),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = weatherDetails.title,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = weatherDetails.getTemDegreeSymbol(),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = weatherDetails.day,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = weatherDetails.time,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
        Image(
            painter = painterResource(id = weatherDetails.timeIcon),
            contentDescription = stringResource(id = R.string.time_icon),
            modifier = Modifier
                .padding(spacing.small)
                .size(spacing.large)
                .align(Alignment.TopEnd)
        )
    }
}