package com.nguyenhoangthanhan.canvascustomui.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nguyenhoangthanhan.canvascustomui.navigation.CanvasCustomUIRouter
import com.nguyenhoangthanhan.canvascustomui.navigation.Screen
import com.nguyenhoangthanhan.canvascustomui.navigation.SystemBackButtonHandler
import com.nguyenhoangthanhan.canvascustomui.ui.theme.blueGray
import com.nguyenhoangthanhan.canvascustomui.ui.theme.brightBlue
import com.nguyenhoangthanhan.canvascustomui.ui.theme.darkGray
import com.nguyenhoangthanhan.canvascustomui.ui.theme.gray
import com.nguyenhoangthanhan.canvascustomui.ui.theme.green
import com.nguyenhoangthanhan.canvascustomui.ui.theme.orange
import com.nguyenhoangthanhan.canvascustomui.ui.theme.purple
import com.nguyenhoangthanhan.canvascustomui.ui.theme.redOrange
import com.nguyenhoangthanhan.canvascustomui.ui.theme.white
import kotlin.math.roundToInt

@Composable
fun BarChart3DScreen() {
    var showDescription by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.7f))
            .padding(30.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Preferred Programming languages",
                fontWeight = FontWeight.Bold,
                color = white,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
            BarChart3D(
                inputList = listOf(
                    BarchartInput(28, "Kotlin", orange),
                    BarchartInput(15, "Swift", brightBlue),
                    BarchartInput(11, "Ruby", green),
                    BarchartInput(7, "Cobol", purple),
                    BarchartInput(14, "C++", blueGray),
                    BarchartInput(9, "C", redOrange),
                    BarchartInput(21, "Python", darkGray),
                ),
                modifier = Modifier.fillMaxWidth(),
                showDescription = showDescription
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Show description",
                    color = white,
                    fontWeight = FontWeight.SemiBold
                )
                Switch(
                    checked = showDescription,
                    onCheckedChange = {
                        showDescription = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = orange,
                        uncheckedThumbColor = white
                    )
                )
            }
        }
    }

    SystemBackButtonHandler {
        CanvasCustomUIRouter.navigateTo(Screen.HomeScreen)
    }
}

@Composable
fun BarChart3D(
    inputList: List<BarchartInput>,
    modifier: Modifier,
    showDescription: Boolean
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val listSun by remember {
            mutableStateOf(inputList.sumOf { it.value })
        }
        inputList.forEach { input ->
            var percentage = input.value / listSun.toFloat()
            Bar(
                modifier = Modifier
                    .height(120.dp * percentage * inputList.size)
                    .width(40.dp),
                primaryColor = input.color,
                percentage = percentage,
                description = input.description,
                showDescription = showDescription
            )
        }
    }
}

@Composable
fun Bar(
    modifier: Modifier,
    primaryColor: Color,
    percentage: Float,
    description: String,
    showDescription: Boolean
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                val width = size.width
                val height = size.height
                val barWidth = width / 5 * 3
                val barHeight = height / 8 * 7
                val barHeight3DPart = height - barHeight
                val barWidth3DPart2 = (width - barWidth) * (height * 0.004f)
                val barWidth3DPart = (width - barWidth)

                var path = Path().apply {
                    moveTo(0f, height)
                    lineTo(barWidth, height)
                    lineTo(barWidth, height - barHeight)
                    lineTo(0f, height - barHeight)
                    close()
                }
                drawPath(
                    path = path,
                    brush = Brush.linearGradient(
                        colors = listOf(gray, primaryColor)
                    )
                )
                path = Path().apply {
                    moveTo(barWidth, height - barHeight)
                    lineTo(barWidth3DPart + barWidth, 0f)
                    lineTo(barWidth3DPart + barWidth, barHeight)
                    lineTo(barWidth, height)
                    close()
                }
                drawPath(
                    path = path,
                    brush = Brush.linearGradient(
                        colors = listOf(primaryColor, gray)
                    )
                )
                path = Path().apply {
                    moveTo(0f, barHeight3DPart)
                    lineTo(barWidth, barHeight3DPart)
                    lineTo(barWidth + barWidth3DPart, 0f)
                    lineTo(barWidth3DPart, 0f)
                    close()
                }
                drawPath(
                    path = path,
                    brush = Brush.linearGradient(
                        colors = listOf(gray, primaryColor)
                    )
                )
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        "${(percentage * 100).roundToInt()} %",
                        barWidth / 5f,
                        height + 55f,
                        android.graphics.Paint().apply {
                            color = white.toArgb()
                            textSize = 11.dp.toPx()
                            isFakeBoldText = true
                        }
                    )
                }
                if (showDescription) {
                    drawContext.canvas.nativeCanvas.apply {
                        rotate(
                            degrees = -50f,
                            pivot = Offset(barWidth3DPart - 20, 0f)
                        ) {
                            drawText(
                                description,
                                0f, 0f, android.graphics.Paint().apply {
                                    color = white.toArgb()
                                    textSize = 14.dp.toPx()
                                    isFakeBoldText = true
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}

data class BarchartInput(
    val value: Int,
    val description: String,
    val color: Color
)