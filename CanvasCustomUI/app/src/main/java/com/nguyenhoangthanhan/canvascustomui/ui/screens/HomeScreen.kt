package com.nguyenhoangthanhan.canvascustomui.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nguyenhoangthanhan.canvascustomui.navigation.CanvasCustomUIRouter
import com.nguyenhoangthanhan.canvascustomui.navigation.Screen

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                CanvasCustomUIRouter.navigateTo(Screen.HomeScreen)
            }
        ) {
            Text(text = "Open Home Screen")
        }

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = {
                CanvasCustomUIRouter.navigateTo(Screen.MeasureInternetSpeechScreen)
            }
        ) {
            Text(text = "Open Measure Internet Speech Screen")
        }

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = {
                CanvasCustomUIRouter.navigateTo(Screen.LiveClockScreen)
            }
        ) {
            Text(text = "Open Live Clock Screen")
        }

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = {
                CanvasCustomUIRouter.navigateTo(Screen.BarChart3DScreen)
            }
        ) {
            Text(text = "Open Bar Chart 3D Screen")
        }

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = {
                CanvasCustomUIRouter.navigateTo(Screen.ClickableCalendarScreen)
            }
        ) {
            Text(text = "Open Clickable Calendar Screen")
        }

    }
}