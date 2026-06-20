package com.example.greenai.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.greenai.R

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DiseaseScan : Screen("disease_scan")
    object Suggestion : Screen("suggestion")
    object Chatbot : Screen("chatbot")
}

data class BottomNavItem(
    val screen: Screen,
    val label: String,
    val icon: Int
)

val bottomNavItems = listOf(
    BottomNavItem(Screen.Home, "Home", R.drawable.home),
    BottomNavItem(Screen.DiseaseScan, "Scan", R.drawable.camera_icon),
    BottomNavItem(Screen.Suggestion, "Suggest", R.drawable.leaf),
    BottomNavItem(Screen.Chatbot, "Chat", R.drawable.chat_bubble)
)