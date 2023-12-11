package developer.selfcoderspackage.expensetracker.views

//Create a composable for the home page. It should show a profile picture and the logged user in the topbar.
// The body should have an account balance, a list of transactions and a button to add a new transaction.

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import developer.selfcoderspackage.expensetracker.R
import developer.selfcoderspackage.expensetracker.model.Screens
import developer.selfcoderspackage.expensetracker.model.variables.drawerItems
import developer.selfcoderspackage.expensetracker.viewmodel.FirestoreViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavHostController) {
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }
    val viewModel = FirestoreViewModel()
    var income by remember { mutableStateOf(0) }
    var expense by remember { mutableStateOf(0) }
    val context = LocalContext.current

    LaunchedEffect(context) {
        val allExpenses = viewModel.getExpensesFromFirestore()

        income = allExpenses
            .filter { it.type == "credit" }
            .sumBy { it.amount.toInt() }

        expense = allExpenses
            .filter { it.type == "debit" }
            .sumBy { it.amount.toInt() }

    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet { DrawerContent(navController)}
        },
        gesturesEnabled = true,
        scrimColor = colorResource(R.color.colorPrimary),
        modifier = Modifier
            .navigationBarsPadding()
            .width(250.dp)

    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        Column {
                            IconButton(onClick = { /*TODO*/ }) {
                                AsyncImage(
                                    model = "https://img.freepik.com/premium-vector/website-analytics-speed-test-with-charts_662093-260.jpg?size=626&ext=jpg&ga=GA1.1.505893937.1701764979&semt=ais",
                                    contentDescription =null,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(40.dp)
                                    )
//                                Icon(Icons.Filled.Person, contentDescription = "Search")
                            }
                            Text(
                                text = "developer2@gmail.com",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Thin,
                                modifier = Modifier
                                    .offset(x = (-5).dp)

                                )
                        }

                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        colorResource(id = R.color.colorPrimary),
                        Color.White,
                        Color.White,
                        Color.White,
                        Color.White
                    ),
                )
            },
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy((-30).dp),
                ) {
                    //Add a box to start immediately after the top app bar without leaving a space. The box should have a curved shape at the bottom
                    Surface(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .navigationBarsPadding(),
                        shape = AbsoluteRoundedCornerShape(
                            bottomLeftPercent = 20,
                            bottomRightPercent = 20
                        ),
                        color = colorResource(id = R.color.colorPrimary)
                    ) {
                        Text(text = ".")
                    }

                       Box{
                            OutlinedCard(
                                onClick = {
//                                    openDialog.value = !openDialog.value
                                },
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(250.dp)
                                    .padding(start = 10.dp, end = 10.dp, top = 2.dp)
                                    .offset(y = (-65).dp),
                                colors = CardDefaults.outlinedCardColors()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize() // Ensure the contents fill the available space
                                        .padding(10.dp),
                                    verticalArrangement = Arrangement.Top,
//                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(
                                        text = "EQTY BANK",
                                        textAlign = TextAlign.End,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.offset(140.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Box(
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(40.dp)
                                            .clip(RoundedCornerShape(3.dp))
                                            .background(Color(0xFFC47162))
                                            .align(alignment = Alignment.Start)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Card Number:\n467************6783",
                                        textAlign = TextAlign.Start,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(3.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "NAFULA JEMTAI MOKISU",
                                            textAlign = TextAlign.Start,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Thin,
                                            color = Color(0xFF059B86)
                                        )
                                        Icon(
                                            Icons.Filled.ArrowDropDown,
                                            contentDescription = "Holder",
                                            tint = Color(0xFF059B86)
                                        )
                                    }

                                }
                            }
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(2.dp),
                               horizontalArrangement = Arrangement.spacedBy(5.dp)
                           ){
                               FilledIconButton(
                                   onClick = { /*TODO*/ },
                                   enabled = false,
                                   shape = RoundedCornerShape(1.dp),
                                   modifier = Modifier.width(200.dp)) {
                                   Text(text = "Total Income: \n\tKshs. $income ")
                               }
                               FilledIconButton(
                                   onClick = { /*TODO*/ },
                                   enabled = false,
                                   shape = RoundedCornerShape(1.dp),
                                   modifier = Modifier.width(200.dp)) {
                                   Text(text = "Total Expenses: \n\tKshs.$expense ")
                               }

                           }
                           Spacer(modifier = Modifier.height(4.dp))
                }

                        Column(
                            modifier = Modifier
//                                .padding(innerPadding)
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState(), true),
//                                .navigationBarsPadding(),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            MonthlyExpenses()
                            Spacer(modifier = Modifier.height(2.dp))
                            OutlinedButton(onClick = {
                                navController.navigate(Screens.UserScreen.route)
                            }) {
                                Text(text = "User Screen")

                            }
                            }
                        }
                        if (openDialog.value == true) {
                            ShowDialog()
                        }


            }
        )

    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage(navController = NavHostController(LocalContext.current))
}

//Define the drawer content
//include the  following with their appropriate icons
// Incomes, Expenses, Categories, Settings, About, Help,Profile,Account, Logout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(navController: NavHostController) {
    Column {

        val selectedvalue = remember{ mutableIntStateOf(0) }

        Card(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(250.dp)
                .height(150.dp),
//            colors = CardDefaults.cardColors(
//                Color.White)

            ) {
            Column {
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .padding(5.dp)
                        .clip(CircleShape)
//                        .background(Color(0xFF059B86))
                        .align(alignment = Alignment.Start)
                )
                {
                    AsyncImage(
                        model = "https://img.freepik.com/free-photo/businessman-sitting-with-laptop-notebook-piggy-bank-funds-isolated-white-background_126523-3112.jpg?size=626&ext=jpg&ga=GA1.1.505893937.1701764979&semt=ais",
                        contentDescription = null)

                }
                Box(
                    modifier = Modifier
                        .width(650.dp)
                        .height(450.dp)
//                        .clip(RoundedCornerShape(3.dp))
//                        .background(Color(0xFFC47162))
                        .offset(x = 140.dp, y = (20).dp)
                        .align(alignment = Alignment.End)
                )
                {
                    AsyncImage(
                        model = "https://img.freepik.com/premium-vector/weather-app-concept-illustration_65141-414.jpg?w=360",
                        contentDescription = null)
                }

            }
            
        }
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            color = Color(0xFF059B86), thickness = 1.dp, modifier = Modifier
                .width(250.dp))
        Spacer(modifier = Modifier.height(5.dp))



        //Add the top part of the drawer
        drawerItems.forEachIndexed { i, drawerItems ->
            NavigationDrawerItem(
                label = { Text(text = drawerItems.name) },
                selected = selectedvalue.intValue == i ,
                onClick = {
                    selectedvalue.intValue = i
                    navController.navigate(drawerItems.route)
                          },
            icon = {
                Icon(
                    drawerItems.icon,
                    contentDescription = "Search",
                )
            },
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp)

            )

            Divider(
                color = Color(0xFF059B86), thickness = 1.dp, modifier = Modifier
                    .width(250.dp))

        }

    }

}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowDialog() {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        Box(
            modifier = Modifier.fillMaxSize()

        ) {
            BottomSheetScaffold(
                sheetContent = {BottomSheetContent()},
                sheetPeekHeight = 250.dp,
                sheetBackgroundColor = Color.Cyan,
                sheetGesturesEnabled = true,
                content = {
                    // Add the rest of the content here
                },
                modifier = Modifier

                )
        }
    }
}


@Composable
fun BottomSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "User Profile", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Name:\nDeveloper")
        Text(text = "Email:\ndeveloper1@gmail.com")
        Text(text = "Phone:\n+234 000 000 0000")
        Text(text = "Address:\nNo 1, Developer Street, Lagos, Nigeria")
        // Add other user profile details as needed
    }
}