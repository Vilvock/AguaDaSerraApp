package com.aguadaserra.app.global_model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Photo (

    @field:SerializedName("foto")
    var photo: String? = null,

): GlobalModel(), Serializable{


}