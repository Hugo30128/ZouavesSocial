package fr.isen.zouavesteam.isensocialnetwork

import android.R
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
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
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityAddPostBinding
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityPostPageBinding


class AddPostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPostBinding
    val IMAGE_REQUEST_CODE = 100
    val database = Firebase.database
    val storage = Firebase.storage.reference
    val storageRef = storage.child("/image")
    private var idPost =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("AddPostActivity")
        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#425B8A")))
        /* RECUPERATION DES INFO*/

        binding.Download.setOnClickListener {
            // class Post constructor(val description: String, val id: Int,val postContent: String, val username: String){}
            val username = intent.getStringExtra("USER") ?: ""
            val description: String = binding.Post.getText().toString()
            val title:String=binding.Post.getText().toString()
            val img: Uri? =null
            val Post = Post(description,img,title,username)
            val myRef = database.getReference("POST").child("POST")
            val intent=Intent(this,PostPageActivity::class.java)
            startActivity(intent)
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
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.imgDownload.setImageURI(data?.data)
            val photo = storageRef.child("imgPost"+idPost.toString())
            data?.data?.let { photo.putFile(it) }

        }

    }




}
