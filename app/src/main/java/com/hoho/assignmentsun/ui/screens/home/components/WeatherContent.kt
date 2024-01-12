package com.hoho.assignmentsun.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.MutableLiveData
import com.hoho.assignmentsun.R
import com.hoho.assignmentsun.model.WeatherDetails
import com.hoho.assignmentsun.networkService.ApiResult
import com.hoho.assignmentsun.ui.theme.LocalSpacing

@Composable
fun WeatherContent(weatherData: MutableLiveData<ApiResult<List<WeatherDetails>>>) {
    val weatherState = weatherData.observeAsState().value
    Column {
        Text(
            text = stringResource(id = R.string.upcoming_slots),
            style = MaterialTheme.typography.headlineSmall
                .copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = LocalSpacing.current.medium)
        )
        LazyRow {
            items((weatherState?.data?: emptyList())) {
                SlotCard(
                    weatherDetails = it
                )
            }
        }
    }
}