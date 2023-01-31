package fr.isen.zouavesteam.isensocialnetwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import javax.crypto.spec.PSource

internal abstract class NetworkAdapter(
    private var itemsList: ArrayList<Post>,
    val onItemClickListener: (PostPageActivity) -> Unit
) :RecyclerView.Adapter<NetworkAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.TITLE)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList
        holder.itemTextView.text = item.toString()
    }


    fun refreshList(dataToExtract: ArrayList<Post>) {
        itemsList = dataToExtract
        notifyDataSetChanged()
    }

}

