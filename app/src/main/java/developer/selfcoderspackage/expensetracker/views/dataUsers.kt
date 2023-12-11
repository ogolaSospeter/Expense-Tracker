package developer.selfcoderspackage.expensetracker.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import developer.selfcoderspackage.expensetracker.model.dataClasses.Credits
import developer.selfcoderspackage.expensetracker.model.dataClasses.Expenses
import developer.selfcoderspackage.expensetracker.model.dataClasses.User
import developer.selfcoderspackage.expensetracker.viewmodel.FirestoreViewModel

// Function to retrieve user data from Firestore


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserList(navController:NavHostController) {
    var users by remember { mutableStateOf(emptyList<User>()) }
    var expenses by remember { mutableStateOf(emptyList<Expenses>()) }

    // LaunchedEffect to fetch user data when the composable is first launched
    LaunchedEffect(true) {
        val viewmodel = FirestoreViewModel()
        users = viewmodel.getUsersFromFirestore()
        expenses = viewmodel.getExpensesFromFirestore()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(text = "Data Items")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = {innerPadding->
    LazyColumn(
        modifier = Modifier.padding(innerPadding)
    ) {
        items(users) { user ->
            UserListItem(user)
        }
        items(expenses) { expense ->
            ExpenseListItem(expense)
        }
    }
}
)
}



@Composable
fun UserListItem(user: User) {
    // Display user information in a Compose card
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "ID: ${user.id}")
            Text(text = "First Name: ${user.firstName}")
            Text(text = "Last Name: ${user.lastName}")
            Text(text = "Born: ${user.born}")
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseListItem(expense: Expenses) {
    val navController: NavHostController = rememberNavController()

    // Display user information in a Compose card
    Column{
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Card(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),

            shape = RoundedCornerShape(3.dp),
            elevation = CardDefaults.cardElevation(),
            colors = CardDefaults.elevatedCardColors(
                Color.White,
            ),
            enabled = true
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                //convert the timestamp to a date and time
                val date = expense.id.toDate()

                Text(text = "Created @: $date")
                Text(text = "Name: ${expense.name}")
                Text(text = "Amount: ${expense.amount}")
            }
        }
    }
            }

}

@Composable
fun CreditListItem(credit: Credits) {
    // Display user information in a Compose card
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            //convert the timestamp to a date and time
            val date = credit.id.toDate()

            Text(text = "Created @: $date")
            Text(text = "Name: ${credit.name}")
            Text(text = "Amount: ${credit.amount}")
        }
    }
}