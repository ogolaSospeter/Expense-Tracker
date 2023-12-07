package developer.selfcoderspackage.expensetracker.model.variables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.twotone.AccountBox
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.icons.twotone.ExitToApp
import androidx.compose.material.icons.twotone.Settings
import developer.selfcoderspackage.expensetracker.model.Screens
import developer.selfcoderspackage.expensetracker.model.dataClasses.DrawerItems


val drawerItems = listOf(
    DrawerItems(
        name = "Home",
        icon = Icons.Filled.Home,
        route = Screens.HomeScreen.route
    ),
    DrawerItems(
        name = "Expenses",
        icon = Icons.TwoTone.AccountCircle,
        route = Screens.DebitScreen.route
    ),
    DrawerItems(
        name = "Incomes",
        icon = Icons.TwoTone.AccountBox,
        route = Screens.CreditScreen.route
    ),
    DrawerItems(
        name = "Categories",
        icon = Icons.TwoTone.DateRange,
        route = Screens.UserScreen.route
    ),
    DrawerItems(
        name = "Settings",
        icon = Icons.TwoTone.Settings,
        route = Screens.SettingsScreen.route
    ),

    DrawerItems(
        name = "Logout",
        icon = Icons.TwoTone.ExitToApp,
        route = Screens.WelcomeScreen.route
    )



)