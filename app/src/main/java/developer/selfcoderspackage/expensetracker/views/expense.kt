package developer.selfcoderspackage.expensetracker.views

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.substring
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import developer.selfcoderspackage.expensetracker.R
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
        expenses = viewModel.getExpensesFromFirestore().sortedBy { it.name }
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
                Column {
                    Box(
                        modifier = Modifier
                            .width(33.dp)
                            .height(29.dp)
                            .background(
                                if (item.type == "debit") {
                                    Color.Red
                                } else {
                                    colorResource(id = R.color.colorPrimary)
                                },
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center,

                        ) {

                        Text(
                            text = item.name[0].toString().uppercase(),
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(2.dp)

                        )
                    }
                }
                Column{
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(35.dp)
                        .height(35.dp)
                        .clip(CircleShape)
//                    colors = IconButtonDefaults.filledIconButtonColors(),

                ) {
                    if (item.type == "credit") {
                        AsyncImage(
//                            model = "https://cdn-icons-mp4.flaticon.com/512/7740/7740575.mp4",
                            model = R.drawable.debit,
                            contentDescription = "Credit",
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp),
                            colorFilter = ColorFilter.tint(colorResource(id = R.color.colorPrimary))
                        )
                    } else {

                        AsyncImage(
//                            model = "https://cdn-icons-mp4.flaticon.com/512/7740/7740559.mp4",
                            model = R.drawable.credit,
                            contentDescription = "Debit",
                            modifier = Modifier
                                .width(20.dp)
                                .weight(5.0F, true)
                                .height(20.dp),
                            colorFilter = ColorFilter.tint(Color.Red)
                        )
                    }

                }
            }
                Column {
                    if (item.name.length <= 15) {
                        Text(
                            text = item.name,
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                        //Display the date and time of the expense
                        Text(
                            text = item.id.toDate().toString().substring(0,20),
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Thin
                            )
                        )
                    } else {
                        Text(
                            text = item.name.substring(0, 0),
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            overflow = TextOverflow.Ellipsis
                        )
                        //Display the date and time of the expense
                        Text(
                            text = item.id.toDate().toString().substring(0,20),
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Thin
                            )
                        )
                    }
                }
                Column {
                    Text(
                        text = "Kshs. ${item.amount}",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Thin
                        )
                    )
                }
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