package com.hoho.assignmentsun.ui.screens.home.homeScreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hoho.assignmentsun.R
import com.hoho.assignmentsun.ui.screens.components.AppTopBar
import com.hoho.assignmentsun.ui.screens.home.components.DurationContent
import com.hoho.assignmentsun.ui.screens.home.components.HomeBottomSheetContentScreen
import com.hoho.assignmentsun.ui.screens.home.components.WeatherContent
import com.hoho.assignmentsun.ui.theme.LocalSpacing
import com.hoho.assignmentsun.utils.DateUtil
import com.hoho.assignmentsun.viewmodels.HomeViewModel
import com.hoho.assignmentsun.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SunHomeScreen(
    mainViewModel: MainViewModel, homeViewModel: HomeViewModel = hiltViewModel()
) {
    LaunchedEffect(homeViewModel){
        homeViewModel.getWeatherData()
    }
    val sunriseDataState = mainViewModel.sunRiseData.observeAsState()
    sunriseDataState.value?.data?.let {
        homeViewModel.sunrise.value = DateUtil.convertIsoToTime(it.sunrise ?: "-")
        homeViewModel.sunset.value = DateUtil.convertIsoToTime(it.sunset ?: "-")
    }
    val spacing = LocalSpacing.current
    val animationSpec = remember {
        Animatable(0f)
            .run {
                TweenSpec<Float>(durationMillis = 300)
            }
    }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = animationSpec,
        confirmStateChange = { false },
        skipHalfExpanded = false
    )
    val openSheet: () -> Unit = {
        scope.launch {
            sheetState.show()
        }
    }
    val closeSheet: () -> Unit = {
        scope.launch {
            sheetState.hide()
        }
    }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            HomeBottomSheetContentScreen(isStarted = homeViewModel.isStarted) {
                if (homeViewModel.isStarted.value) {
                    homeViewModel.cancelTimer()
                    closeSheet.invoke()
                } else {
                    scope.launch {
                        homeViewModel.startTimer(2)
                    }
                }
            }
        },
        sheetShape = MaterialTheme.shapes.large,
        scrimColor = Color.Transparent,
        sheetBackgroundColor = Color.Transparent,
        modifier = Modifier.fillMaxHeight(0.7f)
    ) {
        Column {
            AppTopBar(
                title = mainViewModel.stateCountryName.value,
                onLeadingIconClick = {

                }) {
            }
            Column(modifier = Modifier.padding(spacing.small)) {
                WeatherContent(homeViewModel.weatherData)
                DurationContent(homeViewModel) {
                    openSheet.invoke()
                }
                Text(text = "${stringResource(id = R.string.sunrise)} ${homeViewModel.sunrise.value}")
                Text(text = "${stringResource(id = R.string.sunset)} ${homeViewModel.sunset.value}")
            }
        }
    }
}

