package fr.isen.zouavesteam.isensocialnetwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

public class DetailPostAdapter (
    private var Commentaire: ArrayList<Post>, val onItemClickListener: (PostDetailActivity) -> Unit):RecyclerView.Adapter<DetailPostAdapter.MyViewHolder>() {

        inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var commentaireUser: TextView = view.findViewById(R.id.userCommentaire)
            var commentaire:TextView=view.findViewById(R.id.contentCommentaire)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPostAdapter.MyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.commentaire, parent, false)
            return MyViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return Commentaire.size
        }

        override fun onBindViewHolder(holder: DetailPostAdapter.MyViewHolder, position: Int) {
            val commentaire= Commentaire[position]
            holder.commentaire.text= commentaire.description
            holder.commentaireUser.text=commentaire.user
        }


        fun refreshList(dataToExtract: ArrayList<Post>) {
            Commentaire = dataToExtract
            notifyDataSetChanged()
        }


    }
