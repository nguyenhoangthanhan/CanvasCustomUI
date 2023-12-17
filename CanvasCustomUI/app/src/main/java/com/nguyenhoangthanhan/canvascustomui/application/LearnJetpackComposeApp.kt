package com.nguyenhoangthanhan.canvascustomui.application

import android.app.Activity
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nguyenhoangthanhan.canvascustomui.navigation.CanvasCustomUIRouter
import com.nguyenhoangthanhan.canvascustomui.navigation.Screen
import com.nguyenhoangthanhan.canvascustomui.ui.screens.BarChart3DScreen
import com.nguyenhoangthanhan.canvascustomui.ui.screens.ClickableCalendarScreen
import com.nguyenhoangthanhan.canvascustomui.ui.screens.HomeScreen
import com.nguyenhoangthanhan.canvascustomui.ui.screens.LiveClockScreen
import com.nguyenhoangthanhan.canvascustomui.ui.screens.MeasureInternetSpeechScreen

@Composable
fun CanvasCustomUIApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Crossfade(
            targetState = CanvasCustomUIRouter.currentScreen,
            label = ""
        ) { currentScreen ->
            when (currentScreen.value) {
                is Screen.HomeScreen -> {
                    HomeScreen()
                }

                is Screen.MeasureInternetSpeechScreen -> {
                    MeasureInternetSpeechScreen()
                }

                is Screen.LiveClockScreen -> {
                    LiveClockScreen()
                }

                is Screen.BarChart3DScreen -> {
                    BarChart3DScreen()
                }

                is Screen.ClickableCalendarScreen -> {
                    ClickableCalendarScreen()
                }
            }
        }
    }
}