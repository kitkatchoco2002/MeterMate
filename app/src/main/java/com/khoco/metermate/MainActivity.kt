@file:OptIn(ExperimentalMaterial3Api::class)

package com.khoco.metermate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.khoco.metermate.dashboard.Dashboard
import com.khoco.metermate.navigation.MainScreen
import com.khoco.metermate.ui.theme.MeterMateTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeterMateTheme {
                MainScreen() //UI
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MeterScreenPreview() {
    MeterMateTheme(darkTheme = true) {
        MainScreen()
    }
}