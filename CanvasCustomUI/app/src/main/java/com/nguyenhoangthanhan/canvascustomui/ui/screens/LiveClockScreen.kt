package com.nguyenhoangthanhan.canvascustomui.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nguyenhoangthanhan.canvascustomui.navigation.CanvasCustomUIRouter
import com.nguyenhoangthanhan.canvascustomui.navigation.Screen
import com.nguyenhoangthanhan.canvascustomui.navigation.SystemBackButtonHandler
import com.nguyenhoangthanhan.canvascustomui.ui.theme.darkGray
import com.nguyenhoangthanhan.canvascustomui.ui.theme.gray
import com.nguyenhoangthanhan.canvascustomui.ui.theme.redOrange
import com.nguyenhoangthanhan.canvascustomui.ui.theme.white
import java.lang.Math.PI
import java.util.Calendar
import java.util.Date
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun LiveClockScreen() {
    Box(
        modifier = Modifier
            .background(white)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Clock(
            modifier = Modifier.size(500.dp),
            time = {
                System.currentTimeMillis()
            },
            circleRadius = 300f,
            outerCircleThickness = 25f
        )
    }

    SystemBackButtonHandler {
        CanvasCustomUIRouter.navigateTo(Screen.HomeScreen)
    }
}

@Composable
@Preview
fun PreviewLiveClockScreen() {
    LiveClockScreen()
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
                drawCircle(
                    color = gray,
                    radius = 15f,
                    center = circleCenter
                )

                val littleLineLength = circleRadius * 0.1f
                val largeLineLength = circleRadius * 0.2f

                for (i in 0 until 60) {
                    val angleInDegrees = i * 360f / 60
                    val angleInRad = angleInDegrees * PI / 180f + PI / 2f
                    val lineLength = if (i % 5 == 0) {
                        largeLineLength
                    } else {
                        littleLineLength
                    }
                    val lineThickness = if (i % 5 == 0) 5f else 2f

                    val start = Offset(
                        x = (circleRadius * cos(angleInRad) + circleCenter.x).toFloat(),
                        y = (circleRadius * sin(angleInRad) + circleCenter.y).toFloat()
                    )
                    val end = Offset(
                        x = (circleRadius * cos(angleInRad) + circleCenter.x).toFloat(),
                        y = (circleRadius * sin(angleInRad) + lineLength + circleCenter.y).toFloat()
                    )
                    rotate(
                        angleInDegrees + 180,
                        pivot = start,
                        block = {
                            drawLine(
                                color = gray,
                                start = start,
                                end = end,
                                strokeWidth = lineThickness.dp.toPx()
                            )
                        }
                    )
                }
                val clockHands = listOf(ClockHand.Seconds, ClockHand.Minutes, ClockHand.Hours)

                clockHands.forEach { clockHand ->
                    val angleInDegrees = when (clockHand) {
                        ClockHand.Seconds -> {
                            seconds * 360f / 60f
                        }

                        ClockHand.Minutes -> {
                            (minutes + seconds / 60f) * 360f / 60f
                        }

                        ClockHand.Hours -> {
                            (((hours % 12f) / 12f * 60) * minutes / 12f) * 360f / 60
                        }
                    }
                    val lineLength = when (clockHand) {
                        ClockHand.Seconds -> {
                            circleRadius * 0.8f
                        }

                        ClockHand.Minutes -> {
                            circleRadius * 0.7f
                        }

                        ClockHand.Hours -> {
                            circleRadius * 0.5f
                        }
                    }
                    val lineThickness = when (clockHand) {
                        ClockHand.Seconds -> {
                            3f
                        }

                        ClockHand.Minutes -> {
                            7f
                        }

                        ClockHand.Hours -> {
                            9f
                        }
                    }
                    val start = Offset(
                        x = circleCenter.x,
                        y = circleCenter.y
                    )
                    val end = Offset(
                        x = circleCenter.x,
                        y = circleCenter.y + lineLength
                    )
                    rotate(
                        angleInDegrees + 180,
                        pivot = start,
                        block = {
                            drawLine(
                                color = if (clockHand == ClockHand.Seconds){
                                    redOrange
                                }else{
                                    gray
                                },
                                start = start,
                                end = end,
                                strokeWidth = lineThickness.dp.toPx()
                            )
                        }
                    )
                }
            }
        )
    }
}

enum class ClockHand {
    Seconds, Minutes, Hours
}