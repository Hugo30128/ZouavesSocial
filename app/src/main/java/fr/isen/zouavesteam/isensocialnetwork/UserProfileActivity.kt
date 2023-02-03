package fr.isen.zouavesteam.isensocialnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityPostPageBinding
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityUserProfileBinding


class UserProfileActivity : AppCompatActivity() {
    private lateinit var etext: EditText
    private lateinit var BTN: Button
    private lateinit var dbR: DatabaseReference
    private lateinit var binding: ActivityUserProfileBinding
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val email = intent.getStringExtra("USER") ?: ""
        Firebase.database.getReference("profiles").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.children.map { it.getValue<Profile>() }
                val key = snapshot.children.map { it.key }
                var flag = 0
                println("KEY :" + key.toString())
                for (eachPost in value) {
                    if (email == eachPost?.email) {
                        println("USER : " + eachPost?.username)
                        binding.Nom2.text = eachPost?.username
                        binding.Age2.text = eachPost?.tempsDeJeu
                        binding.Description2.text = eachPost?.description2
                        binding.contact2.text = eachPost?.contact
                        binding.main2.text = eachPost?.main
                        binding.type2.text = eachPost?.type
                        flag = 1
                        break
                    } else {
                        println("Not HIM")
                    }
                }
                if (flag != 1) {
                    Firebase.database.getReference("profiles").push()
                        .setValue(Profile(email, "null", "null", "null", "null", "null", "null"))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })





        binding.reloadInfo.setOnClickListener {
            val intent = Intent(this, ChangeProfilActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

        binding.adduserRedirect.setOnClickListener {
            val intent = Intent(this, AddPostActivity::class.java)
            startActivity(intent)
        }

        binding.homeuser.setOnClickListener {
            val intent = Intent(this, PostPageActivity::class.java)
            startActivity(intent)
        }

        binding.personnage2.setOnClickListener {
            Toast.makeText(applicationContext, "Vous êtes déja sur cette page", Toast.LENGTH_SHORT)
                .show();
        }

    }
}