import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class ResultsViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = Firebase.auth
    private var savedUsername: String = ""



    fun saveResults(nick: String, sumFact01: Int, sumFact02: Int, sumFact03: Int, completion: (Boolean) -> Unit) {
        val newIdRef = db.collection("counters").document("resultsCounter")

        newIdRef.get().addOnSuccessListener { document ->
            val newId = (document.getLong("count") ?: 0) + 1


            val results = hashMapOf(
                "id" to newId,
                "nick" to nick,
                "fecha" to Date().toString(),
                "sumFact01" to sumFact01,
                "sumFact02" to sumFact02,
                "sumFact03" to sumFact03
            )

            db.collection("results")
                .add(results)
                .addOnSuccessListener {
                    newIdRef.update("count", FieldValue.increment(1))
                    completion(true)
                }
                .addOnFailureListener {
                    completion(false)
                }
        }.addOnFailureListener {
            completion(false)
        }
    }

    fun saveUsername(username: String) {
        savedUsername = username
    }
}
