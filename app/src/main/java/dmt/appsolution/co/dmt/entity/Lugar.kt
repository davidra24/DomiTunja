package dmt.appsolution.co.dmt.entity

import java.io.Serializable

/**
 * Created by Martin on 8/01/2018.
 */

class Lugar (id_lugar: Int, email: String, descripcion: String, barrio: String, calificacion: Double, votos: Int,
             telefono: String, celular: String, direccion: String, website: String, ubicacionlugar: String,
             idtipo_lugar: Int, persona_email: String, id_ciudad: Int, nombre: String, matricula: String,
             enabled: Int,  locationX: Double, locationY: Double): Serializable{
    var id_lugar: Int? = id_lugar
    var email: String? = email
    var descripcion: String? = descripcion
    var barrio: String? = barrio
    var calificacion: Double = calificacion
    var votos: Int = votos
    var telefono: String? = telefono
    var celular: String? = celular
    var direccion: String? = direccion
    var website: String? = website
    var ubicacionlugar: String? = ubicacionlugar
    var idtipo_lugar: Int = idtipo_lugar
    var persona_email: String? = persona_email
    var id_ciudad: Int = id_ciudad
    var nombre: String? = nombre
    var matricula: String? = matricula
    var enabled: Int = enabled
    var locationX: Double = locationX
    var locationY: Double = locationY

}
