package com.nguyenhoangthanhan.canvascustomui.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    data object HomeScreen: Screen()
    data object MeasureInternetSpeechScreen: Screen()
    data object LiveClockScreen: Screen()
    data object BarChart3DScreen: Screen()
    data object ClickableCalendarScreen: Screen()
    data object CircularProgressIndicatorScreen: Screen()
}

object CanvasCustomUIRouter{
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.HomeScreen)

    fun navigateTo(screen: Screen){
        currentScreen.value = screen
    }
}