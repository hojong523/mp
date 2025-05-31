package com.example.projectmap.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

sealed class BottomNavItem(
    val label: String,
    val icon: @Composable (modifier: Modifier, color: Color) -> Unit
) {
    object Home : BottomNavItem("홈", { modifier, color ->
        Icon(Icons.Default.Home, contentDescription = "홈", tint = color, modifier = modifier)
    })
    object Map : BottomNavItem("지도", { modifier, color ->
        Icon(Icons.Default.Place, contentDescription = "지도", tint = color, modifier = modifier)
    })
    object Call : BottomNavItem("상담·전화", { modifier, color ->
        Icon(Icons.Default.Phone, contentDescription = "상담·전화", tint = color, modifier = modifier)
    })
    object Guardian : BottomNavItem("보호자", { modifier, color ->
        Icon(Icons.Default.Favorite, contentDescription = "보호자", tint = color, modifier = modifier)
    })
    object Settings : BottomNavItem("설정", { modifier, color ->
        Icon(Icons.Default.Settings, contentDescription = "설정", tint = color, modifier = modifier)
    })
}

val BottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Map,
    BottomNavItem.Call,
    BottomNavItem.Guardian,
    BottomNavItem.Settings
)
