package dmt.appsolution.co.dmt.services.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Martin on 8/01/2018.
 */
class TipoLugar {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("__v")
    @Expose
    var v : Int? = null

    constructor(id: String, name: String, v: Int){
        this.id = id
        this.name = name
        this.v = v
    }
}