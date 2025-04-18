@file:OptIn(ExperimentalMaterial3Api::class)

package com.khoco.metermate.navigation


import Calculation
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.khoco.metermate.analysis.Analytics
import com.khoco.metermate.dashboard.Dashboard
import com.khoco.metermate.ui.theme.Metermate
import androidx.compose.runtime.getValue



@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        Screen.Dashboard,
        Screen.Calculation,
        Screen.Analytics
    )

    Scaffold(
        topBar = {
            when (currentRoute) {
                Screen.Dashboard.route -> {
                    CenterAlignedTopAppBar(title = {
                        Text("MeterMate", style = Metermate.titleLarge)
                    })
                }

                Screen.Calculation.route -> {
                    CenterAlignedTopAppBar(title = {
                        Text("Calculation", style = Metermate.titleLarge)
                    })
                }

                Screen.Analytics.route -> {
                    CenterAlignedTopAppBar(title = {
                        Text("Analytics", style = Metermate.titleLarge)
                    })
                }

                else -> {} // Optional: no top bar
            }
        },
        bottomBar = {
            NavigationBar {
                val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Dashboard.route) { Dashboard() }
            composable(Screen.Calculation.route) { Calculation() }
            composable(Screen.Analytics.route) { Analytics() }
        }
    }
}
