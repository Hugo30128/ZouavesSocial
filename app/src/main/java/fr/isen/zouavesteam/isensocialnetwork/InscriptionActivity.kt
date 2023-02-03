package fr.isen.zouavesteam.isensocialnetwork

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityInscriptionBinding

class InscriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInscriptionBinding
    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityInscriptionBinding.inflate(layoutInflater)
        println("Inscription Activity")
        setContentView(binding.root)
        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = Firebase.auth
        // [END initialize_auth]

        //val actionBar = supportActionBar
        //actionBar?.title = "Legends of Maximus Inscription"


        //supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#425B8A")))

        onStart()



        binding.inscriptionButton.setOnClickListener(){
            println("Clique Bouton")
            val username=binding.userNameText.getText().toString()
            var pwd=binding.userPasswordText.text.toString()
            val pwd2=binding.userPasswordText2.text.toString()
            if(username.isNotEmpty()){
                if(pwd==pwd2){
                    createAccount(username,pwd)
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

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user,email)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null,email)
                }
            }
        // [END create_user_with_email]
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