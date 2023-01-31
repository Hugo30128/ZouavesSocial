package fr.isen.zouavesteam.isensocialnetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.widget.ImageView

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val actionBar = supportActionBar
        actionBar!!.title = "Maximus"

        val button = findViewById<Button>(R.id.age)
        val textView = findViewById<TextView>(R.id.Age2)

        button.setOnClickListener {
            val input = EditText(this)
            val dialog = AlertDialog.Builder(this)
                .setTitle("Modifier le temps de jeu :")
                .setView(input)
                .setPositiveButton("OK") { _, _ ->
                    textView.text = input.text
                }
                .setNegativeButton("Annuler") { _, _ -> }
                .create()
            dialog.show()
        }

        val button2 = findViewById<Button>(R.id.coeur)
        val textView2 = findViewById<TextView>(R.id.main2)

        button2.setOnClickListener {
            val input = EditText(this)
            val dialog = AlertDialog.Builder(this)
                .setTitle("Modifier le champion principal :")
                .setView(input)
                .setPositiveButton("OK") { _, _ ->
                    textView2.text = input.text
                }
                .setNegativeButton("Annuler") { _, _ -> }
                .create()
            dialog.show()
        }

        val button3 = findViewById<Button>(R.id.typeplayer)
        val textView3 = findViewById<TextView>(R.id.type)

        button3.setOnClickListener {
            val input = EditText(this)
            val dialog = AlertDialog.Builder(this)
                .setTitle("Modifier le type de joueur :")
                .setView(input)
                .setPositiveButton("OK") { _, _ ->
                    textView3.text = input.text
                }
                .setNegativeButton("Annuler") { _, _ -> }
                .create()
            dialog.show()
        }

        val button4 = findViewById<Button>(R.id.pseudo)
        val textView4 = findViewById<TextView>(R.id.contact2)

        button4.setOnClickListener {
            val input = EditText(this)
            val dialog = AlertDialog.Builder(this)
                .setTitle("Modifier le pseudo de jeu :")
                .setView(input)
                .setPositiveButton("OK") { _, _ ->
                    textView4.text = input.text
                }
                .setNegativeButton("Annuler") { _, _ -> }
                .create()
            dialog.show()
        }

       val button5 = findViewById<Button>(R.id.livre)
        val textView5 = findViewById<TextView>(R.id.Description2)

        button5.setOnClickListener {
            val input = EditText(this)
            val dialog = AlertDialog.Builder(this)
                .setTitle("Modifier la description :")
                .setView(input)
                .setPositiveButton("OK") { _, _ ->
                    textView5.text = input.text
                }
                .setNegativeButton("Annuler") { _, _ -> }
                .create()
            dialog.show()
        }
    }
}