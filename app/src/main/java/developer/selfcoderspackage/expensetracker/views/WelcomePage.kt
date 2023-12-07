package developer.selfcoderspackage.expensetracker.views

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.AsyncImage
import developer.selfcoderspackage.expensetracker.model.Screens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun WelcomePage(navController:NavHostController) {
    //generate a random name using the random name generator module
    val name = "Developer"
    // Create a mutable state to track the swiped state
        // Content of the page
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color(0xFF059B86))
            //enable the horizontal swipe gesture

    ) {
        Text(text = "Welcome Back, ${name}.\nManage Your Expense Tracker",
            fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
            fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
            fontSize = 13.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(20.dp))
        AsyncImage(
            model = "https://t4.ftcdn.net/jpg/05/75/84/29/240_F_575842934_ZwkoVVxiJ67QS6etpz4SaiaIAHGsP9s9.jpg",
            contentDescription = "Image",
            imageLoader = ImageLoader.Builder(context = LocalContext.current).build(),
            modifier = Modifier
                .clip(CircleShape)
                .size(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = { navController.navigate(Screens.HomeScreen.route) },
            colors = ButtonDefaults.outlinedButtonColors(),
            modifier = Modifier
                .width(150.dp),
            border = BorderStroke(1.dp, Color.White),

            ){
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                Icon(Icons.Filled.Home, contentDescription = "Menu", tint = Color.White, modifier = Modifier.size(20.dp))
                Text(text = "Start Tracking", color = Color.White, fontSize = 10.sp, modifier = Modifier.padding(5.dp))
            }
            }

        }

    }



    //preview
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        WelcomePage(navController = NavHostController(LocalContext.current))
    }


