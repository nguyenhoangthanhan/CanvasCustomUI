package com.nguyenhoangthanhan.canvascustomui.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.nguyenhoangthanhan.canvascustomui.navigation.CanvasCustomUIRouter
import com.nguyenhoangthanhan.canvascustomui.navigation.Screen
import com.nguyenhoangthanhan.canvascustomui.navigation.SystemBackButtonHandler
import com.nguyenhoangthanhan.canvascustomui.ui.theme.darkGray
import com.nguyenhoangthanhan.canvascustomui.ui.theme.white
import java.util.Calendar
import java.util.Date

@Composable
fun LiveClockScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white),
        contentAlignment = Alignment.Center
    ) {

        Clock(
            modifier = Modifier.size(500.dp),
            time = {
                System.currentTimeMillis()
            },
            circleRadius =,
            outerCircleThickness =
        )
    }

    SystemBackButtonHandler {
        CanvasCustomUIRouter.navigateTo(Screen.HomeScreen)
    }
}

@Composable
fun Clock(
    modifier: Modifier = Modifier,
    time: () -> Long,
    circleRadius: Float,
    outerCircleThickness: Float
) {

    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    Box(
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                val width = size.width
                val height = size.height
                circleCenter = Offset(
                    x = width / 2f,
                    y = height / 2f
                )
                val date = Date(time())
                val cal = Calendar.getInstance()
                cal.time = date
                val hours = cal.get(Calendar.HOUR_OF_DAY)
                val minutes = cal.get(Calendar.MINUTE)
                val seconds = cal.get(Calendar.SECOND)

                drawCircle(
                    style = Stroke(
                        width = outerCircleThickness
                    ),
                    brush = Brush.linearGradient(
                        listOf(
                            white.copy(0.45f),
                            darkGray.copy(0.35f)
                        )
                    ),
                    radius = circleRadius + outerCircleThickness / 2f,
                    center = circleCenter
                )
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(
                            white.copy(0.45f),
                            darkGray.copy(0.25f)
                        )
                    ),
                    radius = circleRadius,
                    center = circleCenter
                )
            }
        )
    }
}