package com.example.concierge.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.EvStation
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.concierge.ConciergeApplication
import com.example.concierge.ui.dashboard.DashboardScreen
import com.example.concierge.ui.entry.EntryScreen
import com.example.concierge.ui.history.HistoryScreen
import com.example.concierge.ui.stats.StatsScreen

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Default.EvStation)
    object History : Screen("history", "History", Icons.Default.History)
    object Entry : Screen("entry", "Entry", Icons.AutoMirrored.Filled.ListAlt)
    object Stats : Screen("stats", "Stats", Icons.Default.QueryStats)
}

val items = listOf(
    Screen.Dashboard,
    Screen.History,
    Screen.Entry,
    Screen.Stats,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: @Composable () -> Unit) {
    CenterAlignedTopAppBar(
        title = title,
    )
}
@Composable
fun ConciergeBottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 8.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            val selected = currentRoute == screen.route
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.label
                    )
                },
                label = { Text(screen.label) },
                selected = selected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Composable
fun ConciergeApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val repository = (context.applicationContext as ConciergeApplication).repository
    val viewModel: FuelLogsViewModel = viewModel(factory = FuelLogsViewModelFactory(repository))

    Scaffold(
        topBar = { TopAppBar(title = { Text("Trl.in") }) },
        bottomBar = { ConciergeBottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) { DashboardScreen() }
            composable(Screen.History.route) { HistoryScreen(viewModel) }
            composable(Screen.Entry.route) { EntryScreen(viewModel) }
            composable(Screen.Stats.route) { StatsScreen() }
        }
    }
}
