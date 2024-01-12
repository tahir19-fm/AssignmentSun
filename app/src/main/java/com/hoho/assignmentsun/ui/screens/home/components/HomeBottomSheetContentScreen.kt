package com.hoho.assignmentsun.ui.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeBottomSheetContentScreen(
    isStarted: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick.invoke()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = if (!isStarted.value) Color.Green else Color.Red),
        modifier = Modifier.fillMaxWidth()
            .height(80.dp)
    ) {
        Text(text = if (!isStarted.value) "START" else "STOP")
    }
}