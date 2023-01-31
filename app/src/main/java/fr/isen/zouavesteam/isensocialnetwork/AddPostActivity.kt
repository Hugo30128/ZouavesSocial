package fr.isen.zouavesteam.isensocialnetwork

import android.R
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityAddPostBinding
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityPlatpageBinding


class AddPostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("AddPostActivity")

        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        class DataPost constructor(val User: String, val url: String, val Post: String) {}
        val name = intent.extras?.getString("USER") ?: "No message found"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#425B8A")))
        println(name)
        binding.Titre.text=name
        /* RECUPERATION DES INFO*/
        var cpt=0
        binding.Download.setOnClickListener {
            val title: String = binding.Post.getText().toString()
            println(title)
            val url: String = binding.URL.getText().toString()
            println(url)
            val img: ImageView = binding.imgDownload
            Picasso.get().load(url).into(img);
            /* ENVOIE DE L'INFO GRACE A LA CLASSE DATAPOST*/
            val OnePost = DataPost(name, url, title)
            println(OnePost.User + OnePost.Post + OnePost.url)
            cpt++
            val database = Firebase.database
            val myRef = database.getReference("POST").child("USER"+cpt)
            myRef.setValue(OnePost)
            val intent=Intent(this,platpage::class.java)
            startActivity(intent)
        }

    }
}