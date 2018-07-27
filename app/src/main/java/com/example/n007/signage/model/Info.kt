package com.example.n007.signage.model

import com.google.gson.annotations.SerializedName

/**
 * Created by N007 on 27/4/2561.
 */
class Info {
    data class content(
            @SerializedName("name") var _name: String?,
            @SerializedName("type") var _type: String?,
            @SerializedName("time") var _time: String?)

    data class photos(

            @SerializedName("url_big")  var _urlBig: String?,
            @SerializedName("url_small") var _urlSmall: String)


}
