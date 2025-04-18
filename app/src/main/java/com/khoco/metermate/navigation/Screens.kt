package com.khoco.metermate.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Filled.Home)
    object Calculation : Screen("calculation", "Calculation", Icons.Filled.Calculate)
    object Analytics : Screen("analytics", "Analytics", Icons.Filled.BarChart)
}
