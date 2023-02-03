package fr.isen.zouavesteam.isensocialnetwork

import android.net.Uri


data class Post(
    var id: String? = "",
    var description: String? = null,
    var img: String? = null,
    var title: String? = null,
    var user: String? = null,
    var like: String? = null,
    var dislike: String? = null,
    var comments: ArrayList<String> = arrayListOf()
) :java.io.Serializable