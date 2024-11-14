package com.rhyttah.nextgen_school.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rhyttah.nextgen_school.ui.theme.screens.login.LoginScreen
import com.rhyttah.nextgen_school.ui.theme.screens.models.Student
import com.rhyttah.nextgen_school.ui.theme.screens.projectbody.AddScreen
import com.rhyttah.nextgen_school.ui.theme.screens.projectbody.AnnounceScreen
import com.rhyttah.nextgen_school.ui.theme.screens.projectbody.HomeScreen
import com.rhyttah.nextgen_school.ui.theme.screens.projectbody.UpdateScreen
import com.rhyttah.nextgen_school.ui.theme.screens.projectbody.ViewScreen
import com.rhyttah.nextgen_school.ui.theme.screens.register.RegisterScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_LOGIN,
    studentList: MutableList<Student> = mutableListOf() // Assuming a default empty list for demonstration
) {
    NavHost(
        navController = navController,
        modifier = Modifier,
        startDestination = startDestination
    ) {
        composable(ROUTE_LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(ROUTE_REGISTER) {
            RegisterScreen(navController = navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(navController = navController)
        }

        composable(ROUTE_ADD) {
            AddScreen(navController = navController, studentList = studentList)
        }

        composable(ROUTE_VIEW) {
            ViewScreen(navController = navController, studentList = studentList)
        }

        composable(ROUTE_UPDATE) {
            UpdateScreen(navController = navController, studentList = studentList)
        }

        composable(ROUTE_ANNOUNCE) {
            AnnounceScreen(navController = navController)
        }
    }
}

