package fr.isen.zouavesteam.isensocialnetwork

import android.R
import android.content.Intent
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


class AddPostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        class DataPost constructor(val User: String, val url: String, val Post: String) {}
        val name = intent.extras?.getString("USER") ?: "No message found"
        println(name)
        binding.Titre.text=name
        /* RECUPERATION DES INFO*/
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
            val database = Firebase.database
            val myRef = database.getReference("POST")
            myRef.setValue(OnePost)
        }

    }
}