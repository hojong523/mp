package com.example.projectmap.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projectmap.data.BottomNavItems

private val SelectedBgColor = Color(0xFFFFD099)
private val Transparent = Color(0x00000000)

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFFFFF5E3), // 바 전체 배경
        tonalElevation = 8.dp
    ) {
        // 바텀 네비게이션 바
        BottomNavItems.forEachIndexed { index, item ->
            val isSelected = selectedIndex == index
            val bgColor = if (isSelected) SelectedBgColor else Transparent
            val contentColor = Color.Black

            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) },
                icon = {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = bgColor, shape = RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item.icon(Modifier.size(45.dp), contentColor)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = item.label,
                                color = contentColor
                            )
                        }
                    }
                },
                label = { },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Transparent,
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Black
                )
            )
        }
    }
}