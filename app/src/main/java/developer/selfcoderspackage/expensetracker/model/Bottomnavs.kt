package developer.selfcoderspackage.expensetracker.model

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import developer.selfcoderspackage.expensetracker.R

data class Bottomnavs(
    val name: String,
    val icon: ImageVector,
    val route: String,
    val badgecount: Int = 0
    )

val app_bottom_navs = listOf(
    Bottomnavs(
        name = "Home",
        icon = Icons.Filled.Home,
        route = Screens.HomeScreen.route
    ),
    Bottomnavs(
        name = "Profile",
        icon = Icons.Filled.AccountCircle,
        route = Screens.UserScreen.route
    ),
    Bottomnavs(
        name = "Settings",
        icon = Icons.Filled.Settings,
        route = Screens.WelcomeScreen.route
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppNavigation(
    navController: NavHostController,
    bottomnavs: List<Bottomnavs>,
    modifier: Modifier = Modifier,
    onItemClicked: (Bottomnavs) -> Unit
    ){
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation (
        modifier = modifier,
        backgroundColor = colorResource(id = R.color.white),
        contentColor = colorResource(id = R.color.colorPrimary),
        elevation = 16.dp
    ){
        bottomnavs.forEach { bottomnavs ->
            val selected = bottomnavs.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                icon = {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ){
                        if (bottomnavs.badgecount > 0){
                            BadgedBox(badge = {
                                Text(text = bottomnavs.badgecount.toString())
                            }) {
                                Icon(
                                    imageVector = bottomnavs.icon,
                                    contentDescription = bottomnavs.name,
                                    tint = colorResource(id = R.color.colorPrimary)
                                )

                            }
                        }
                        else {
                            Icon(
                                imageVector = bottomnavs.icon,
                                contentDescription = bottomnavs.name,
                                tint = colorResource(id = R.color.colorPrimary)
                            )
                        }
//                        if (selected){
//                            Text(
//                                text = bottomnavs.name,
//                                textAlign = TextAlign.Center,
//                                color = colorResource(id = R.color.black)
//                                )
//                        }
                    }
                },
                selected = selected,
                label = {
                        if (selected){
                            Text(
                                text = bottomnavs.name,
                                textAlign = TextAlign.Center,
                                color = Color.Green
                                )
                        }
                },
                onClick = {
                    onItemClicked(bottomnavs)
                },
                selectedContentColor = colorResource(id = R.color.colorPrimary),
                unselectedContentColor = colorResource(id = R.color.teal_700),
                modifier = modifier,
                alwaysShowLabel = false,
                )
    }
    }
}
