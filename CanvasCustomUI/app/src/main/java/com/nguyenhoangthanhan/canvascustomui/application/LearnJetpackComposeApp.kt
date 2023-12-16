package com.nguyenhoangthanhan.learnjetpackcompose.application

import android.app.Activity
import android.content.Context
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nguyenhoangthanhan.learnjetpackcompose.navigation.LearnJetpackComposeRouter
import com.nguyenhoangthanhan.learnjetpackcompose.navigation.Screen
import com.nguyenhoangthanhan.learnjetpackcompose.ui.screens.DelegationScreen
import com.nguyenhoangthanhan.learnjetpackcompose.ui.screens.HomeScreen
import com.nguyenhoangthanhan.learnjetpackcompose.ui.screens.NavigateBackWithResultScreen

@Composable
fun LearnJetpackComposeApp(activity: Activity) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Crossfade(
            targetState = LearnJetpackComposeRouter.currentScreen,
            label = ""
        ) { currentScreen ->
            when (currentScreen.value) {
                is Screen.HomeScreen -> {
                    HomeScreen()
                }

                is Screen.DelegationScreen -> {
                    DelegationScreen(activity)
                }

                is Screen.NavigateBackWithResultScreen -> {
                    NavigateBackWithResultScreen()
                }
            }
        }
    }
}