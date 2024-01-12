package com.hoho.assignmentsun.ui.screens.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hoho.assignmentsun.R
import com.hoho.assignmentsun.ui.screens.home.components.DurationRecommendationCard
import com.hoho.assignmentsun.ui.theme.BlackTransparent
import com.hoho.assignmentsun.ui.theme.LocalSpacing
import com.hoho.assignmentsun.ui.theme.rainbowColors
import com.hoho.assignmentsun.viewmodels.HomeViewModel

@Composable
fun DurationProgressBar(viewModel: HomeViewModel) {
    val spacing= LocalSpacing.current
    val rem = remember {
        mutableFloatStateOf(0.6f)
    }
    val remainingTime= remember {
        mutableStateOf("10 min")
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(BlackTransparent, shape = RoundedCornerShape(spacing.medium)),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.padding(spacing.small),verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
            Box(contentAlignment = Alignment.Center) {
                DrawArc(progress = viewModel.remainingTime)
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.duration))
                    Spacer(modifier = Modifier.height(spacing.medium))
                    Text(
                        text = "${viewModel.totalTime.value} min",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
                DurationRecommendationCard(
                    constTime = "1 hr 10 min",
                    tool = "Vitamin D",
                    title = "Recommended"
                )
                DurationRecommendationCard(
                    constTime = "1 hr 10 min",
                    tool = "Vitamin D",
                    title = "Daily Goal"
                )
                DurationRecommendationCard(
                    time = viewModel.timerText,
                    tool = "Sunlight",
                    title = "Time Remaining"
                )
            }
        }
    }

}

@Composable
fun DrawArc(progress: MutableState<Float>) {
    val startAngle = 140f
    val sweepAngle = 260f
    val useCenter by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .size(220.dp)
        .padding(LocalSpacing.current.small)) {
        Canvas(modifier = Modifier.size(220.dp)) {
            drawArc(
                color = Color.LightGray,
                startAngle,
                sweepAngle,
                useCenter,
                style = Stroke(width = 30f, cap = StrokeCap.Round, miter = 40f)
            )
            drawArc(
                brush = Brush.sweepGradient(
                    colors = rainbowColors,
                    center = Offset(size.width / 2, size.width)
                ),
                startAngle,
                progress.value,
                useCenter,
                style = Stroke(width = 30f, cap = StrokeCap.Round)
            )
        }
    }
}