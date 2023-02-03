package fr.isen.zouavesteam.isensocialnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityChangeProfilBinding

class ChangeProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangeProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeProfilBinding.inflate(layoutInflater)
        println("ACTIVITY CHANGE PROFIL")
        setContentView(binding.root)
        val email = intent.getStringExtra("email") ?: ""
        println("email : " + email);
        println("profiles/${FirebaseAuth.getInstance().currentUser?.uid}")
        fun onModify(email: String) {
            val username = binding.info1.text.toString()
            val tempsDeJeu = binding.info2.text.toString()
            val main = binding.info3.text.toString()
            val type = binding.info4.text.toString()
            val contact = binding.info5.text.toString()
            val description = binding.info6.text.toString()

            println("profiles/${FirebaseAuth.getInstance().currentUser?.uid}")
            val ref = Firebase.database.getReference("profiles/${FirebaseAuth.getInstance().currentUser?.uid}").setValue(
                Profile(
                    email,
                    username,
                    description,
                    tempsDeJeu,
                    type,
                    contact,
                    main
                )
            )


            val intent = Intent(this, PostPageActivity::class.java)
            startActivity(intent)
        }
        binding.ModifyButton.setOnClickListener {
            onModify(email)
        }

    }

}