package fr.isen.zouavesteam.isensocialnetwork

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]s
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = Firebase.auth
        // [END initialize_auth]
        val actionBar = supportActionBar
        actionBar?.title = "Legends of Maximus"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#425B8A")))
        onStart()
        binding.redirectInscription.setOnClickListener {
            val intent = Intent(this, InscriptionActivity::class.java)
            startActivity(intent)
        }

        binding.connexionButton.setOnClickListener{
            val username=binding.userNameText.text.toString()
            val pwd=binding.userPasswordText.text.toString()
            if(username.isNotEmpty()){
                if(pwd.isNotEmpty()){
                    signIn(username,pwd)
                }
            }
        }



    }
    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }
    // [END on_start_check_user]
    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(InscriptionActivity.TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user,email)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(InscriptionActivity.TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null,email)
                }
            }
        // [END sign_in_with_email]
    }
    private fun updateUI(user: FirebaseUser?, username: String) {
        if(user!=null){
            val intent= Intent(this,AddPostActivity::class.java)
            intent.putExtra("USER",username)
            startActivity(intent)


        }
    }
    private fun reload() {

    }
    companion object {
        const val TAG = "EmailPassword"
    }
}