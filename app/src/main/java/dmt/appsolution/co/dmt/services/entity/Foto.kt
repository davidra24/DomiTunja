package dmt.appsolution.co.dmt.services.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Martin on 25/01/2018.
 */
class Foto {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("id_lugar")
    @Expose
    var idLugar: String? = null
    @SerializedName("__v")
    @Expose
    var v: Int? = null

}