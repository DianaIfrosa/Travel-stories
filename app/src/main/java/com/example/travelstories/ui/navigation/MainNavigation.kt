package com.example.travelstories.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.travelstories.ui.screens.Dashboard
import com.example.travelstories.ui.screens.HolidayScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Dashboard {clickedHoliday ->
                navController.navigate("holidayPage/" + clickedHoliday.id)
            }
        }
        composable("add") {
//            Dashboard()
        }
        composable("settings") {
//            Dashboard()
        }
        composable(
            "holidayPage/{holidayId}",
            arguments = listOf(navArgument("holidayId") { type = NavType.StringType })
        ) {
            HolidayScreen()
        }
    }
}