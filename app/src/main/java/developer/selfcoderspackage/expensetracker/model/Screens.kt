package developer.selfcoderspackage.expensetracker.model

//Define the screen class

sealed class Screens (val route: String){
    object HomeScreen: Screens("home_screen")
    object AddScreen: Screens("add_screen")
    object EditScreen: Screens("edit_screen")
    object DeleteScreen: Screens("delete_screen")
    object ViewScreen: Screens("view_screen")
    object SettingsScreen: Screens("settings_screen")
    object AboutScreen: Screens("about_screen")
    object HelpScreen: Screens("help_screen")
    object ExitScreen: Screens("exit_screen")
    object WelcomeScreen: Screens("welcome_screen")
}