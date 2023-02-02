package fr.isen.zouavesteam.isensocialnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.isen.zouavesteam.isensocialnetwork.databinding.ActivityPostPageBinding


class PostPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPostPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerPostView.adapter = NetworkAdapter(arrayListOf()){}
        binding.recyclerPostView.layoutManager = LinearLayoutManager(this)
        val username = intent.getStringExtra("USER") ?: ""


        intent=Intent(this,AddPostActivity::class.java)
        println("\n\n\nInfo POST PAGE : "+username+"\n\n\n")
        intent.putExtra("USER",username)

        binding.addpostRedirect.setOnClickListener{
            // ButtonAddPostActivity(username)
            val intent = Intent (this, AddPostActivity::class.java)
            startActivity(intent)
        }

        binding.personnage.setOnClickListener{
            val intent = Intent (this, UserProfileActivity::class.java)
            startActivity(intent)
        }

        binding.home.setOnClickListener{
            Toast.makeText(applicationContext, "Vous êtes déja sur cette page", Toast.LENGTH_SHORT).show();
        }
      /*  Firebase.database.getReference("posts").push().setValue(
            Post(
                "ouie",
                "je suis le trois",
                "c'est le troisième",
                "UID"
            )
        )
    */

         Firebase.database.getReference("posts").addValueEventListener(object :ValueEventListener{
              override fun onDataChange(snapshot: DataSnapshot) {
                  val value = snapshot.children.map{ it.getValue<Post>() }
                  val ki = snapshot.children.map{it.key}
                  Log.d("TAG", "key is: " + ki?.first())
                  Log.d("TAG", "Value is: " + value?.first()?.description)
                  handleAPIData(value as ArrayList<Post>)
              }

              override fun onCancelled(error: DatabaseError) {
                  Log.w("TAG", "Failed to read value.", error.toException())
              }
          })

    /*
        findViewById<Button>(R.id.mainAction).setOnClickListener {
            // Write a message to the database
            val database = Firebase.database
            val myRef = database.getReference("message")
            myRef.setValue("Hello, World!")
        }


        */
    }
    private fun ButtonAddPostActivity(User:String){
        intent= Intent(this,AddPostActivity::class.java)
        intent.putExtra("USER",User)
        startActivity(intent)

    }

    private fun handleAPIData(data: ArrayList<Post>) {
           val adapter = binding.recyclerPostView.adapter as NetworkAdapter
           adapter.refreshList(data)
       }
}