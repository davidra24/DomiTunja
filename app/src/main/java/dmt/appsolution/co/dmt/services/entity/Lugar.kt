package dmt.appsolution.co.dmt.services.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Lugar: Serializable {

    @SerializedName("idLugar")
    @Expose
    var idLugar: String? = null
    @SerializedName("nombre")
    @Expose
    var nombre: String? = null
    @SerializedName("descripcion")
    @Expose
    var descripcion: String? = null
    @SerializedName("horario")
    @Expose
    var horario: String? = null
    @SerializedName("calificacion")
    @Expose
    var calificacion: String? = null
    @SerializedName("telefono")
    @Expose
    var telefono: String? = null
    @SerializedName("direccion")
    @Expose
    var direccion: String? = null
    @SerializedName("website")
    @Expose
    var website: String? = null
    @SerializedName("id_ciudad")
    @Expose
    var idCiudad: String? = null
    @SerializedName("idTipo_lugar")
    @Expose
    var idTipoLugar: String? = null
    @SerializedName("ubicacionLugar")
    @Expose
    var ubicacionLugar: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null

    constructor()
}