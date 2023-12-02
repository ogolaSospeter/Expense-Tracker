package developer.selfcoderspackage.expensetracker.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import developer.selfcoderspackage.expensetracker.views.HomePage
import developer.selfcoderspackage.expensetracker.views.WelcomePage

//Define the application navigation composables

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screens.WelcomeScreen.route){
        composable(Screens.WelcomeScreen.route){
            WelcomePage(navController = navController)
        }
        // Home Screen
        composable(Screens.HomeScreen.route){
            HomePage(navController = navController)
        }
    }
}