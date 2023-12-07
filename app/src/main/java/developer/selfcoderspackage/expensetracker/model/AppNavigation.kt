package developer.selfcoderspackage.expensetracker.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import developer.selfcoderspackage.expensetracker.views.Credits
import developer.selfcoderspackage.expensetracker.views.Debits
import developer.selfcoderspackage.expensetracker.views.HomePage
import developer.selfcoderspackage.expensetracker.views.SettingsPage
import developer.selfcoderspackage.expensetracker.views.UserList
import developer.selfcoderspackage.expensetracker.views.WelcomePage

//Define the application navigation composables

@Composable
fun AppNavigation(navController: NavHostController ){
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route){
        composable(Screens.WelcomeScreen.route){
            WelcomePage(navController = navController)
        }
        // Home Screen
        composable(Screens.HomeScreen.route){
            HomePage(navController = navController)
        }
        composable(Screens.UserScreen.route){
            UserList(navController = navController)
        }
        composable(Screens.CreditScreen.route){
            Credits(navController = navController)
        }
        composable(Screens.DebitScreen.route){
            Debits(navController = navController)
        }
        composable(Screens.SettingsScreen.route){
            SettingsPage(navController = navController)
        }

    }
}