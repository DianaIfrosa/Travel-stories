package com.example.travelstories.ui

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.rememberNavController
import com.example.travelstories.R
import com.example.travelstories.ui.navigation.BottomNavigationItem
import com.example.travelstories.ui.navigation.MainNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val navController = rememberNavController()

    val menuItems = listOf(
        BottomNavigationItem(
            title = "add",
            selectedIcon = Icons.Filled.AddCircle,
            unselectedIcon = Icons.Outlined.AddCircle,
            hasNotifications = false
        ),
        BottomNavigationItem(
            title = "home",
            selectedIcon = ImageVector.vectorResource(R.drawable.menu_home_button_filled),
            unselectedIcon = ImageVector.vectorResource(R.drawable.menu_home_button_unfilled),
            hasNotifications = false
        ),
        BottomNavigationItem(
            title = "settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNotifications = true
        ),
    )

    // it is resistant to phone turning
    var selectedMenuItemIndex by rememberSaveable {
        mutableIntStateOf(1) // todo: replace with number initially selected
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                menuItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = (selectedMenuItemIndex == index),
                        onClick = {
                            if (selectedMenuItemIndex != index)
                                navController.navigate(item.title)
                            selectedMenuItemIndex = index
                        },
                        label = { Text(text = item.title) },
                        icon = {
//                            BadgedBox(
//                                badge = {
//                                    if (item.badgeCount != null) {
//                                        Badge {
//                                            Text(text = item.badgeCount.toString())
//                                        }
//                                    } else if (item.hasNotifications) {
//                                        Badge() // just the bubble
//                                    }
//                                }
//                            ) {
                                // a box that can display properly a badge or not
                                Icon(
                                    imageVector = if (index == selectedMenuItemIndex)
                                        item.selectedIcon
                                    else item.unselectedIcon,
                                    contentDescription = item.title,
                                    tint = Color.Unspecified
                                )
//                            }
                        }
                    )
                }
            }
        }
    ) {
        MainNavigation(navController)
    }
}