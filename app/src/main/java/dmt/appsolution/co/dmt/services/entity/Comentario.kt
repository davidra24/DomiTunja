package dmt.appsolution.co.dmt.services.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Comentario {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("id_place")
    @Expose
    var idPlace: String? = null
    @SerializedName("id_user")
    @Expose
    var idUser: String? = null
    @SerializedName("comment")
    @Expose
    var comment: String? = null
    @SerializedName("__v")
    @Expose
    var v: Int? = null

    constructor(id: String, idPlace: String, idUser: String, comment: String){
        this.id = id
        this.idPlace = idPlace
        this.idUser = idUser
        this.comment = comment
    }

}