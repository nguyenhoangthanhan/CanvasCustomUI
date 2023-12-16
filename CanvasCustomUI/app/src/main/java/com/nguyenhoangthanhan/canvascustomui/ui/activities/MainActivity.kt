package com.nguyenhoangthanhan.canvascustomui.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nguyenhoangthanhan.canvascustomui.application.CanvasCustomUIApp
import com.nguyenhoangthanhan.canvascustomui.ui.theme.CanvasCustomUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasCustomUITheme {
                CanvasCustomUIApp()
            }
        }
    }
}