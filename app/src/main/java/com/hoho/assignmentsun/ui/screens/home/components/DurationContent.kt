package com.hoho.assignmentsun.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.hoho.assignmentsun.R
import com.hoho.assignmentsun.ui.screens.components.DurationProgressBar
import com.hoho.assignmentsun.ui.theme.LocalSpacing
import com.hoho.assignmentsun.viewmodels.HomeViewModel

@Composable
fun DurationContent(
    viewModel: HomeViewModel,
    onClick: () -> Unit
) {
    Column {
        Text(
            text = stringResource(id = R.string.total_duration),
            style = MaterialTheme.typography.headlineSmall
                .copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = LocalSpacing.current.medium)
                .clickable {
                    onClick.invoke()
                }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                           onClick.invoke()
                },
            horizontalArrangement = Arrangement.Center
        ) {
            DurationProgressBar(viewModel)
        }
    }

}