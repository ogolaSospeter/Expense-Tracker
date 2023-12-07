package developer.selfcoderspackage.expensetracker.viewmodel

// Create a viewmodel for the firestore

import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import developer.selfcoderspackage.expensetracker.model.dataClasses.Credits
import developer.selfcoderspackage.expensetracker.model.dataClasses.Expenses
import developer.selfcoderspackage.expensetracker.model.dataClasses.SortOrder
import developer.selfcoderspackage.expensetracker.model.dataClasses.User
import kotlinx.coroutines.tasks.await

class FirestoreViewModel : ViewModel() {
    suspend fun getUsersFromFirestore(): List<User> {
        val db = FirebaseFirestore.getInstance()
        val usersCollection = db.collection("users")
        val querySnapshot = usersCollection.get().await()
        return querySnapshot.documents.map { document ->
            val data = document.data
            if (data != null) {
                User(
                    id = document.id,
                    firstName = data["first"] as String,
                    lastName = data["last"] as String,
                    born = (data["born"] as Long).toInt()
                )
            } else {
                // Handle the case where data is null
                User(id = document.id, firstName = "", lastName = "", born = 0)
            }
        }
    }


    suspend fun getExpensesFromFirestore(): List<Expenses> {
        val db = FirebaseFirestore.getInstance()
        val expensesCollection = db.collection("Expenses")
        val querySnapshot = expensesCollection.get().await()

        return querySnapshot.documents.map { document ->
            Expenses(
                id = document["id"] as? Timestamp ?: Timestamp.now(),
                name = document["name"] as? String ?: "",
                amount = (document["amount"] as? Number) ?: 0,
                type = document["type"] as? String ?: ""
            )
        }
    }
    suspend fun getExpensesFromFirestore(sortOrder: SortOrder = SortOrder.ASCENDING): List<Expenses> {
        val db = FirebaseFirestore.getInstance()
        val expensesCollection = db.collection("Expenses")
        val querySnapshot = expensesCollection.orderBy("name", if (sortOrder == SortOrder.ASCENDING) Query.Direction.ASCENDING else Query.Direction.DESCENDING).get().await()

        return querySnapshot.documents.map { document ->
            Expenses(
                id = document["id"] as? Timestamp ?: Timestamp.now(),
                name = document["name"] as? String ?: "",
                amount = (document["amount"] as? Double) ?: 0.0,
                type = document["type"] as? String ?: ""
            )
        }
    }
    // Function to add a new expense to Firestore
    suspend fun addExpenseToFirestore(expense: Expenses) {
        val db = FirebaseFirestore.getInstance()
        val expensesCollection = db.collection("Expenses")
        expensesCollection.add(expense).await()
    }

    // Function to delete an expense from Firestore
    suspend fun deleteExpenseFromFirestore(expense: Expenses) {
        val db = FirebaseFirestore.getInstance()
        val expensesCollection = db.collection("Expenses")
        expensesCollection.document(expense.id.toString()).delete().await()
    }

    // Function to update an expense in Firestore
    suspend fun updateExpenseInFirestore(expense: Expenses) {
        val db = FirebaseFirestore.getInstance()
        val expensesCollection = db.collection("Expenses")
        expensesCollection.document(expense.id.toString()).set(expense).await()
    }

    //Function to retrieve a single expense from Firestore
    suspend fun getExpenseFromFirestore(expenseId: String): Expenses {
        val db = FirebaseFirestore.getInstance()
        val expensesCollection = db.collection("Expenses")
        val querySnapshot = expensesCollection.document(expenseId).get().await()
        return Expenses(
            id = querySnapshot["id"] as? Timestamp ?: Timestamp.now(),
            name = querySnapshot["name"] as? String ?: "",
            amount = (querySnapshot["amount"] as? Number) ?: 0,
            type = querySnapshot["type"] as? String ?: ""
        )
    }


    // Credit Functions
    suspend fun  getCreditsFromFirestore(): List<Credits> {
        val db = FirebaseFirestore.getInstance()
        val creditsCollection = db.collection("Credits")
        val querySnapshot = creditsCollection.get().await()

        return querySnapshot.documents.map { document ->
            Credits(
                id = document["id"] as? Timestamp ?: Timestamp.now(),
                name = document["name"] as? String ?: "",
                amount = (document["amount"] as? Number) ?: 0,
                type = document["type"] as? String ?: ""
            )
        }
    }

    suspend fun getCreditsFromFirestore(sortOrder: SortOrder = SortOrder.ASCENDING): List<Credits> {
        val db = FirebaseFirestore.getInstance()
        val creditsCollection = db.collection("Credits")
        val querySnapshot = creditsCollection.orderBy("name", if (sortOrder == SortOrder.ASCENDING) Query.Direction.ASCENDING else Query.Direction.DESCENDING).get().await()

        return querySnapshot.documents.map { document ->
            Credits(
                id = document["id"] as? Timestamp ?: Timestamp.now(),
                name = document["name"] as? String ?: "",
                amount = (document["amount"] as? Double) ?: 0.0,
                type = document["type"] as? String ?: ""
            )
        }
    }

    suspend fun addCreditToFirestore(credit: Credits) {
        val db = FirebaseFirestore.getInstance()
        val creditsCollection = db.collection("Credits")
        creditsCollection.add(credit).await()
    }

    suspend fun deleteCreditFromFirestore(credit: Credits) {
        val db = FirebaseFirestore.getInstance()
        val creditsCollection = db.collection("Credits")
        creditsCollection.document(credit.id.toString()).delete().await()
    }

    suspend fun updateCreditInFirestore(credit: Credits) {
        val db = FirebaseFirestore.getInstance()
        val creditsCollection = db.collection("Credits")
        creditsCollection.document(credit.id.toString()).set(credit).await()
    }

    suspend fun getCreditFromFirestore(creditId: String): Credits {
        val db = FirebaseFirestore.getInstance()
        val creditsCollection = db.collection("Credits")
        val querySnapshot = creditsCollection.document(creditId).get().await()
        return Credits(
            id = querySnapshot["id"] as? Timestamp ?: Timestamp.now(),
            name = querySnapshot["name"] as? String ?: "",
            amount = (querySnapshot["amount"] as? Number) ?: 0,
            type = querySnapshot["type"] as? String ?: ""
        )
    }

}

