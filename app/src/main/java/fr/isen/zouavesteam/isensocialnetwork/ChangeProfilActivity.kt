package fr.isen.zouavesteam.isensocialnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        binding= ActivityChangeProfilBinding.inflate(layoutInflater)
        println("ACTIVITY CHANGE PROFIL")
        setContentView(binding.root)
        val email = intent.getStringExtra("email") ?: ""

        fun onModify(email: String) {
            val username = binding.info1.text.toString()
            val tempsDeJeu = binding.info2.text.toString()
            val main = binding.info3.text.toString()
            val type = binding.info4.text.toString()
            val contact = binding.info5.text.toString()
            val description = binding.info6.text.toString()

            val ref = Firebase.database.getReference("profiles")
            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val value = snapshot.children.map { it.getValue<Profile>() }
                        val key = snapshot.children.map { it.key }
                        println("Clé"+key.toString())
                        if (snapshot.exists()) {
                            for (profileSnapshot in value) {
                                if (profileSnapshot?.email == email) {
                                    ref.child(key.toString()).child(email).setValue(Profile(email, username, description, tempsDeJeu, type, contact, main));
                                    break
                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Handle error
                    }
                })

            val intent = Intent(this, PostPageActivity::class.java)
            startActivity(intent)
        }
        binding.ModifyButton.setOnClickListener {
            onModify(email)
        }

    }

}