package fr.isen.zouavesteam.isensocialnetwork

import android.net.Uri


data class Post(
    //var postresult: ArrayList<Post>? = arrayListOf(),
    var description: String? = null,
    var img: Uri? = null,
    var title: String? = null,
    var user: String? = null
)