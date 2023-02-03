package fr.isen.zouavesteam.isensocialnetwork

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso


public class NetworkAdapter(
    private var itemsList: ArrayList<Post>,
    val onItemClickListener: (Post/*PostPageActivity*/) -> Unit
) : RecyclerView.Adapter<NetworkAdapter.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.titleTextPostPage)
        var description: TextView = view.findViewById(R.id.description)
        var imageView: ImageView = view.findViewById(R.id.imageView)
        var partage: Button = view.findViewById(R.id.partage)
        var comment: EditText = view.findViewById(R.id.comment)
        var butonlike: ImageView = view.findViewById(R.id.like)
        var butondislike : ImageView = view.findViewById(R.id.dislike)
        var textlike :  TextView = view.findViewById(R.id.LikeView)
        var textdislike :  TextView = view.findViewById(R.id.dislikeView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.title.text = item.title
        holder.description.text = item.description
        holder.textlike.text = item.like
        holder.textdislike.text = item.dislike
        holder.partage.setOnClickListener{
            item.comments.add(holder.comment.text.toString())
            Firebase.database.getReference("posts/${item.id}/comments").setValue(
                item.comments
            )
        }
        holder.imageView.setOnClickListener {
            onItemClickListener(item)
        }

        var storage = FirebaseStorage.getInstance()
        var storageReference = storage.reference.child("image/" + item.img)



        println("LA REFRENCE" + storageReference)
        storageReference.downloadUrl.addOnSuccessListener { uri ->
            println("Image à afficher" + uri + "\n\n\n")
            // L'URL de téléchargement de l'image est disponible ici
            Picasso.get().load(uri).into(holder.imageView)
        }

        holder.butonlike.setOnClickListener {
            val currentValue = holder.textlike.text.toString().toInt()
            val newValue = currentValue + 1
            holder.textlike.text = newValue.toString()
            Firebase.database.getReference("posts/${item.id}/like").setValue(
                newValue.toString()
            )
        }

        holder.butondislike.setOnClickListener {
            val currentValue = holder.textdislike.text.toString().toInt()
            val newValue = currentValue + 1
            holder.textdislike.text = newValue.toString()
            Firebase.database.getReference("posts/${item.id}/dislike").setValue(
                newValue.toString()
            )
        }
    }

    fun refreshList(dataToExtract: ArrayList<Post>) {
        itemsList = dataToExtract
        notifyDataSetChanged()
    }

}

