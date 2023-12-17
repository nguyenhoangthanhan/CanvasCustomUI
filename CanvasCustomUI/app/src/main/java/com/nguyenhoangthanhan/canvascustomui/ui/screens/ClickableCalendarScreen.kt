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
import com.nguyenhoangthanhan.canvascustomui.navigation.SystemBackButtonHandler

@Composable
fun ClickableCalendarScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                CanvasCustomUIRouter.navigateTo(Screen.LiveClockScreen)
            }
        ) {
            Text(text = "Open Live Clock Screen")
        }

    }

    SystemBackButtonHandler {
        CanvasCustomUIRouter.navigateTo(Screen.HomeScreen)
    }
}