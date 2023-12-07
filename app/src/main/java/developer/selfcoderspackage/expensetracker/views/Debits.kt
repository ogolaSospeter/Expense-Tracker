package developer.selfcoderspackage.expensetracker.views

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import developer.selfcoderspackage.expensetracker.model.dataClasses.Expenses
import developer.selfcoderspackage.expensetracker.model.dataClasses.SortOrder
import developer.selfcoderspackage.expensetracker.viewmodel.FirestoreViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Create a debits composable to display the debits screen
fun Debits(navController: NavHostController) {
    var sortOrder by remember { mutableStateOf(SortOrder.ASCENDING) }
    var sortedexpensesList by remember { mutableStateOf(emptyList<Expenses>()) }
    var expenses by remember { mutableStateOf(emptyList<Expenses>()) }
    val viewmodel = FirestoreViewModel()

    LaunchedEffect(sortOrder) {
        // Fetch expenses when sortOrder changes
        expenses = viewmodel.getExpensesFromFirestore()
        sortedexpensesList = viewmodel.getExpensesFromFirestore(sortOrder)

    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Debits")
                },
                navigationIcon = {
                                 navController.navigateUp()
                    // Display the navigation icon
                },
                actions = {
                          //Add a sort button to the top app bar
                          IconButton(onClick = {
                                  // Toggle sort order on button click
                                  sortOrder = if (sortOrder == SortOrder.ASCENDING) SortOrder.DESCENDING else SortOrder.ASCENDING
                              sortedexpensesList = expenses

                          }) {
                              Icon(imageVector = Icons.Sharp.PlayArrow, contentDescription = "Sort")


                          }

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
                )
        },
        content = {

            Column(
                modifier = Modifier.padding(16.dp)
                    .verticalScroll(ScrollState(0), true)
            ) {
                sortedexpensesList.forEach { item ->
                    ExpenseListItem(item)
                }
            }
        }
    )


}


