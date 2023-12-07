package developer.selfcoderspackage.expensetracker.model

//Define the screen class

sealed class Screens (val route: String){
    object HomeScreen: Screens("home_screen")
    object WelcomeScreen: Screens("welcome_screen")
    object UserScreen: Screens("users")
    object CreditScreen: Screens("credit_screen")
    object DebitScreen: Screens("debit_screen")
    object SettingsScreen: Screens("settings_screen")

}