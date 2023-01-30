package fr.isen.zouavesteam.isensocialnetwork

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InscriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)
        val actionBar = supportActionBar
        actionBar?.title = "Legends of Maximus Inscription"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#425B8A")))
    }
}