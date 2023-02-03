package fr.isen.zouavesteam.isensocialnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityPostDetailBinding

@Suppress("DEPRECATION")
class PostDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailBinding
    private lateinit var post : Post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_post_detail)
        binding=ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        post = intent.getSerializableExtra("post") as Post

        binding.adduserdetailRedirect.setOnClickListener{
            // ButtonAddPostActivity(username)
            val intent = Intent (this, AddPostActivity::class.java)
            startActivity(intent)
        }

        binding.homepostdetail.setOnClickListener{
            val intent = Intent (this, PostPageActivity::class.java)
            startActivity(intent)
        }
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.detailCommentaire.layoutManager = layoutManager
        binding.detailCommentaire.adapter = CommentAdapter(post.comments) {
        }

        binding.detailDescription.text=post.description
        var storage = FirebaseStorage.getInstance()
        var storageReference = storage.reference.child("image/" + post.img)



        println("LA REFRENCE" + storageReference)
        storageReference.downloadUrl.addOnSuccessListener { uri ->
            println("Image à afficher" + uri + "\n\n\n")
            // L'URL de téléchargement de l'image est disponible ici
            Picasso.get().load(uri).into(binding.detailImage)

        }


    }
}