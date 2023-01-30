package fr.isen.zouavesteam.isensocialnetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityPlatpageBinding

class platpage : AppCompatActivity() {


    private lateinit var binding: ActivityPlatpageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlatpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerPostView.layoutManager = LinearLayoutManager(this)

    }


}