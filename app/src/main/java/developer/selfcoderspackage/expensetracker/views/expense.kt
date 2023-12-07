package developer.selfcoderspackage.expensetracker.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import developer.selfcoderspackage.expensetracker.model.dataClasses.Credits
import developer.selfcoderspackage.expensetracker.model.dataClasses.Expenses
import developer.selfcoderspackage.expensetracker.viewmodel.FirestoreViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthlyExpenses() {
    val db = Firebase.firestore
    var expenses by remember { mutableStateOf(emptyList<Expenses>()) }
    var credits by remember { mutableStateOf(emptyList<Credits>()) }
    // LaunchedEffect to fetch user data when the composable is first launched
    LaunchedEffect(true) {
        val viewModel = FirestoreViewModel()
        expenses = viewModel.getExpensesFromFirestore()
        credits = viewModel.getCreditsFromFirestore()
    }

Column(
    horizontalAlignment = Alignment.CenterHorizontally,
){
    Text(text = "Your Monthly Expenses", style = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Light
    ),
        textAlign = TextAlign.Center)
    expenses.forEach {item->
        Spacer(modifier = Modifier.height(3.dp))
        Card(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 15.dp, end = 15.dp),

            shape = RoundedCornerShape(3.dp),
            elevation = CardDefaults.cardElevation(7.dp, 7.dp, 7.dp,6.dp,8.dp,3.dp),
            colors = CardDefaults.elevatedCardColors(
                Color.White,
            ),
            enabled = true
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(25.dp)
                        .background(Color.Red, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center,

                    ) {
                    Text(
                        text = item.name[0].toString().uppercase(),
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(2.dp)

                    )
                }
                if (item.name.length <= 15) {
                    Text(
                        text = item.name,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                } else {
                    Text(
                        text = item.name.substring(0, 10),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = "Kshs. ${item.amount}",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Thin
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(3.dp))
//        Divider(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 25.dp, end = 25.dp),
//            color = colorResource(id = R.color.colorPrimary)
//        )
    }
    Spacer(modifier = Modifier.height(10.dp))

}
}


//credits.forEach {item->
//    Spacer(modifier = Modifier.height(3.dp))
//    Card(
//        onClick = { /*TODO*/ },
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(60.dp)
//            .padding(start = 15.dp, end = 15.dp),
//
//        shape = RoundedCornerShape(3.dp),
//        elevation = CardDefaults.cardElevation(7.dp, 7.dp, 7.dp,6.dp,8.dp,3.dp),
//        colors = CardDefaults.elevatedCardColors(
//            Color.White,
//        ),
//        enabled = true
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                modifier = Modifier
//                    .width(30.dp)
//                    .height(25.dp)
//                    .background(Color.Green, shape = RoundedCornerShape(8.dp)),
//                contentAlignment = Alignment.Center,
//
//                ) {
//                Text(
//                    text = item.name[0].toString().uppercase(),
//                    color = Color.White,
//                    style = TextStyle(
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Bold
//                    ),
//                    modifier = Modifier.padding(2.dp)
//
//                )
//            }
//            if (item.name.length <= 15) {
//                Text(
//                    text = item.name,
//                    style = TextStyle(
//                        fontSize = 15.sp,
//                        fontWeight = FontWeight.Medium
//                    )
//                )
//            } else {
//                Text(
//                    text = item.name.substring(0, 10),
//                    style = TextStyle(
//                        fontSize = 15.sp,
//                        fontWeight = FontWeight.Medium
//                    ),
//                    overflow = TextOverflow.Ellipsis
//                )
//            }
//            Text(
//                text = "Kshs. ${item.amount}",
//                style = TextStyle(
//                    fontSize = 13.sp,
//                    fontWeight = FontWeight.Thin
//                )
//            )
//        }
//    }
//    Spacer(modifier = Modifier.height(3.dp))
//    Divider(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 25.dp, end = 25.dp),
//        color = colorResource(id = R.color.colorPrimary)
//    )
//}