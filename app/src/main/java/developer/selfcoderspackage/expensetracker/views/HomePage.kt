package developer.selfcoderspackage.expensetracker.views

//Create a composable for the home page. It should show a profile picture and the logged user in the topbar.
// The body should have an account balance, a list of transactions and a button to add a new transaction.

import android.annotation.SuppressLint
import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(navController:NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Expense Tracker") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Person, contentDescription = "Search")
                    }
                },

            )

        },
        content = {
            // Content of the page
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Track your Finance Spending")
                OutlinedCard(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(150.dp)
                        .width(250.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(20.dp)

                    ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(10.dp)
                    )
                    {
                        Text(text = "Account Balance")
                        Text(text = "$ 0.00")
                        Spacer(modifier = Modifier.height(3.dp))
                        Row{
                            Column {
                                Text(text = "Income")
                                Text(text = "$ 0.00")
                            }
                            Spacer(modifier = Modifier.width(3.dp))
                            Column {
                                Text(text = "Expense")
                                Text(text = "$ 0.00")
                            }

                        }

                    }

                }

            }

        }
    )
}

    //preview
@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage(navController = NavHostController(LocalContext.current))
}
