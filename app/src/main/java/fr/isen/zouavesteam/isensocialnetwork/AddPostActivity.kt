package fr.isen.zouavesteam.isensocialnetwork

import android.R
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityAddPostBinding
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityPostPageBinding
import kotlin.random.Random


@Suppress("DEPRECATION")
class AddPostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPostBinding
    val IMAGE_REQUEST_CODE = 100
    val database = Firebase.database
    val storage = Firebase.storage.reference
    val storageRef = storage.child("/image")
    val key = Random.nextInt(0,100)
    lateinit var img : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("AddPostActivity")
        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#425B8A")))
        val username = intent.getStringExtra("USER") ?: ""
        println("\n\n\nInfo : "+username+"\n\n\n")

        binding.Download.setOnClickListener {

            val description: String = binding.Post.getText().toString()
            val title: String = binding.postTitle.getText().toString()
            var img:String ="gs://isensocialnetwork-zouave.appspot.com/image/"+key.toString()+ ".jpeg"
            val like ="0"
            val dislike = "0"
            val Post = Post(description, img, title, username, like,dislike)
            val intent = Intent(this, PostPageActivity::class.java)
            Firebase.database.getReference("posts").push()
                .setValue(Post(description, img, title, username, like, dislike))
        }


        binding.imgDownload.setOnClickListener {
            pickImageGallery()

        }
    }


    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        println("vouci la cleé é" + key)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.imgDownload.setImageURI(data?.data)
            val photo = storageRef.child(key.toString())
            data?.data?.let { photo.putFile(it) }
        }


    }
}


