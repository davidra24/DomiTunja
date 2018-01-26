package dmt.appsolution.co.dmt.services.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Lugar: Serializable {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("descripcion")
    @Expose
    var descripcion: String? = null
    @SerializedName("barrio")
    @Expose
    var barrio: String? = null
    @SerializedName("calificacion")
    @Expose
    var calificacion: Double? = null
    @SerializedName("votos")
    @Expose
    var votos: Int? = null
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
    @SerializedName("ubicacion")
    @Expose
    var ubicacion: String? = null
    @SerializedName("id_tipo_lugar")
    @Expose
    var idTipoLugar: String? = null
    @SerializedName("persona_email")
    @Expose
    var personaEmail: String? = null
    @SerializedName("id_ciudad")
    @Expose
    var idCiudad: String? = null
    @SerializedName("nombre")
    @Expose
    var nombre: String? = null
    @SerializedName("habilitado")
    @Expose
    var habilitado: Boolean? = null
    @SerializedName("matricula")
    @Expose
    var matricula: String? = null
    @SerializedName("ubicacionX")
    @Expose
    var ubicacionX: Double? = null
    @SerializedName("ubicacionY")
    @Expose
    var ubicacionY: Double? = null
    @SerializedName("__v")
    @Expose
    var v: Int? = null

    constructor()
}