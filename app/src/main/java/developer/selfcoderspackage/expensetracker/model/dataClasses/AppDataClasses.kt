package developer.selfcoderspackage.expensetracker.model.dataClasses

import androidx.compose.ui.graphics.vector.ImageVector
import com.google.firebase.Timestamp

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val born: Int
)

data class Expenses(
    val id: Timestamp,
    val name: String,
    val amount: Number,
    var type: String
)

data class Credits(
    val id:Timestamp,
    val name:String,
    val amount: Number,
    val type: String
)

enum class SortOrder {
    ASCENDING,
    DESCENDING
}

data class DrawerItems(
    val name: String,
    val icon: ImageVector,
    val route: String
)









//// Create a new user with a first and last name
//val user = hashMapOf(
//    "first" to "Ada",
//    "last" to "Lovelace",
//    "born" to 1815
//)
//
//// Add a new document with a generated ID
//db.collection("users")
//.add(user)
//.addOnSuccessListener { documentReference ->
//    Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//}
//.addOnFailureListener { e ->
//    Log.w(ContentValues.TAG, "Error adding document", e)
//}
//db.collection("users")
//.get()
//.addOnSuccessListener { result ->
//    for (document in result) {
//        Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
//    }
//}
//.addOnFailureListener { exception ->
//    Log.w(ContentValues.TAG, "Error getting documents.", exception)
//}
//
////Display the data from the database

