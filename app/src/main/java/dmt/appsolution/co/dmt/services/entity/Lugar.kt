package dmt.appsolution.co.dmt.services.entity

import java.io.Serializable

/**
 * Created by Martin on 8/01/2018.
 */

class Lugar : Serializable{
    var id_lugar: Int = 0
    var email: String? = ""
    var descripcion: String? = ""
    var barrio: String? = ""
    var calificacion: Double = 0.0
    var votos: Int = 0
    var telefono: String? = ""
    var celular: String? = ""
    var direccion: String? = ""
    var website: String? = ""
    var ubicacionlugar: String? = ""
    var idtipo_lugar: Int = 0
    var persona_email: String? = ""
    var id_ciudad: Int = 0
    var nombre: String? = ""
    var matricula: String? = ""
    var enabled: Int = 0
    var locationX: Double = 0.0
    var locationY: Double = 0.0

    constructor(id_lugar: Int, email: String, descripcion: String, barrio: String, calificacion: Double, votos: Int,
                telefono: String, celular: String, direccion: String, website: String, ubicacionlugar: String,
                idtipo_lugar: Int, persona_email: String, id_ciudad: Int, nombre: String, matricula: String,
                enabled: Int,  locationX: Double, locationY: Double){
        this.id_lugar = id_lugar
        this.email = email
        this.descripcion = descripcion
        this.barrio = barrio
        this.calificacion = calificacion
        this.votos = votos
        this.telefono = telefono
        this.celular = celular
        this.direccion = direccion
        this. website = website
        this.ubicacionlugar = ubicacionlugar
        this.idtipo_lugar = idtipo_lugar
        this.persona_email = persona_email
        this.id_ciudad = id_ciudad
        this.nombre = nombre
        this.matricula = matricula
        this.enabled = enabled
        this.locationX = locationX
        this.locationY = locationY
    }

    constructor()
}