package dmt.appsolution.co.dmt.services.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Martin on 30/01/2018.
 */
class LugarDetalles {
    @SerializedName("nombre")
    @Expose
    var nombre: String? = null
    @SerializedName("descripcion")
    @Expose
    var descripcion: String? = null
    @SerializedName("barrio")
    @Expose
    var barrio: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("calificacion")
    @Expose
    var calificacion: String? = null
    @SerializedName("votos")
    @Expose
    var votos: String? = null
    @SerializedName("telefono")
    @Expose
    var telefono: String? = null
    @SerializedName("celular")
    @Expose
    var celular: String? = null
    @SerializedName("direccion")
    @Expose
    var direccion: String? = null
    @SerializedName("website")
    @Expose
    var website: String? = null
    @SerializedName("ubicacionLugar")
    @Expose
    var ubicacionLugar: String? = null
    @SerializedName("idTipo_lugar")
    @Expose
    var idTipoLugar: String? = null
    @SerializedName("id_ciudad")
    @Expose
    var idCiudad: String? = null
    @SerializedName("fotos")
    @Expose
    var fotos: List<String>? = null

    constructor()
}