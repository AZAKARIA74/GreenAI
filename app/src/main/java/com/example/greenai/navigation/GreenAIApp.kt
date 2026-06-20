package com.example.greenai.navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.greenai.presentation.viewmodel.DiseaseViewModel
import com.example.greenai.presentation.viewmodel.SuggestionViewModel
import com.example.greenai.ui.screen.ChatbotScreen
import com.example.greenai.ui.screen.DiseaseScanScreen
import com.example.greenai.ui.screen.HomeScreen
import com.example.greenai.ui.screen.HomeTab
import com.example.greenai.ui.screen.SuggestionScreen
import com.greenai.ui.theme.GreenAITheme

@Composable
fun GreenAIApp(
    diseaseViewModel: DiseaseViewModel,
    suggestionViewModel: SuggestionViewModel
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { GreenAIBottomBar(navController = navController) }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.DiseaseScan.route) {
                DiseaseScanScreen(
                    viewModel = diseaseViewModel,
                    onBackClick = { navController.popBackStack(navController.graph.findStartDestination().id, false) }
                )
            }
            composable(Screen.Suggestion.route) {
                SuggestionScreen(
                    viewModel = suggestionViewModel,
                    onBackClick = { navController.popBackStack(navController.graph.findStartDestination().id, false) }
                )
            }
            composable(Screen.Chatbot.route) {
                ChatbotScreen()
            }
        }
    }
}

@Composable
private fun GreenAIBottomBar(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route



    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp
    ) {

        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(painter = painterResource(item.icon) , contentDescription = item.label) },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = .2f),
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = .6f),
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = .6f)
                ),
                alwaysShowLabel = true
            )
        }
    }
    HorizontalDivider(
        thickness = 0.5.dp,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
    )
}

private fun NavController.navigateToTab(tab: HomeTab) {
    val route = when (tab) {
        HomeTab.HOME -> Screen.Home.route
        HomeTab.SCAN -> Screen.DiseaseScan.route
        HomeTab.SUGGEST -> Screen.Suggestion.route
        HomeTab.CHAT -> Screen.Chatbot.route
    }
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}


@Preview(showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreenAIBottomBarPreview() {
    GreenAITheme {
        GreenAIBottomBar(
            navController = rememberNavController()

        )
    }
}
