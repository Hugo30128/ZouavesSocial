package fr.isen.zouavesteam.isensocialnetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
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


       // Firebase.database.getReference("posts").push().setValue(Post("j'ai perdu wlh", "image de moi qui perd", "Jsuis trop nul de ouf", "UID"))




       Firebase.database.getReference("posts").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.children.map{ it.getValue<Post>() }
                Log.d("TAG", "Value is: " + value?.first()?.description)
                findViewById<TextView>(R.id.textView).text=value?.first()?.description
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

 /*   private fun handleAPIData(data: Post) {
        val adapter = binding.recyclerPostView.adapter as NetworkAdapter
        adapter.refreshList(?.items as ArrayList<Post>)
    }
    */
}