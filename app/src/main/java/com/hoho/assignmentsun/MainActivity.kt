package com.hoho.assignmentsun

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.hoho.assignmentsun.ui.screens.home.homeScreen.SunHomeScreen
import com.hoho.assignmentsun.ui.theme.AssignmentSunTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLocationPermission()
        mainViewModel.getUserLocation(this)
        setContent {
            MyApp {
                SunHomeScreen(mainViewModel)
            }
        }
    }


}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    AssignmentSunTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Image(
                painter = painterResource(id = R.drawable.background_app),
                contentDescription = stringResource(id = R.string.app_background),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            content.invoke()
        }
    }
}

