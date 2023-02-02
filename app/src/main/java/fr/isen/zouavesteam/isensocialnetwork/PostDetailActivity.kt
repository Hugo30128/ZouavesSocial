package fr.isen.zouavesteam.isensocialnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityPostDetailBinding

class PostDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        binding=ActivityPostDetailBinding.inflate(layoutInflater)

        binding.adduserdetailRedirect.setOnClickListener{
            // ButtonAddPostActivity(username)
            val intent = Intent (this, AddPostActivity::class.java)
            startActivity(intent)
        }

        binding.homepostdetail.setOnClickListener{
            val intent = Intent (this, PostPageActivity::class.java)
            startActivity(intent)
        }
    }
}