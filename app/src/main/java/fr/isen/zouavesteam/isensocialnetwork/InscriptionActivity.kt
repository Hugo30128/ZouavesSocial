package fr.isen.zouavesteam.isensocialnetwork

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityInscriptionBinding

class InscriptionActivity : AppCompatActivity() {
    private lateinit var binding:ActivityInscriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)
        val actionBar = supportActionBar
        actionBar?.title = "Legends of Maximus Inscription"
        class UserData constructor(var username: String, val pwd: String) {}
        val intent= Intent(this,AddPostActivity::class.java)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#425B8A")))
        binding=ActivityInscriptionBinding.inflate(layoutInflater)
        println("Inscription Activity")
        binding.connexionButton.setOnClickListener {
            println("Clique Bouton")
            val username=binding.userNameText.getText().toString()
            var pwd="random"
            val User:UserData=UserData(username,pwd)
            intent.putExtra("USER",User.toString())
            println("Start New Activity")
            startActivity(intent)
        }

    }
}