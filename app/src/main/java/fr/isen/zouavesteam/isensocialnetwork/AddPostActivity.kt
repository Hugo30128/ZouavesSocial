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
        val name = intent.extras?.getString("USER") ?: "No message found"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#425B8A")))


        /* RECUPERATION DES INFO*/
        binding.Download.setOnClickListener {
            class Post constructor(val description: String, val urlImg: String,val img : String,val postContent: String){}
            val description: String = binding.Post.getText().toString()
            val title:String=binding.Post.getText().toString()
            val url: String = binding.URL.getText().toString()
            val img: ImageView = binding.imgDownload
            Picasso.get().load(url).into(img);
            val creationPost:Post = Post(description,name, url, title)
            val database = Firebase.database
            /*.push() créer un nouvel identifiant,setValue()*/
            val myRef = database.getReference("POST").child("POST 2")
            val intent=Intent(this,platpage::class.java)
            startActivity(intent)
        }
    }
}