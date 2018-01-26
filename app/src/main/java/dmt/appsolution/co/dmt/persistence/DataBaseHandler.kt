package dmt.appsolution.co.dmt.persistence

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import dmt.appsolution.co.dmt.constants.Constants
import dmt.appsolution.co.dmt.services.entity.Lugar

class DataBaseHandler(var context: Context): SQLiteOpenHelper (context, Constants.DB_NAME, null, Constants.DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        val creaateTableLugar = "CREATE TABLE IF NOT EXISTS " + Constants.DB_TABLE_FAV + "(" +
                "  `idLugar` varchar(11) NOT NULL," +
                "  `email` varchar(180)," +
                "  `descripcion` varchar(180)," +
                "  `barrio` varchar(340) DEFAULT NULL, " +
                "  `calificacion` float DEFAULT '0'," +
                "  `votos` int(11) NOT NULL DEFAULT '3'," +
                "  `telefono` varchar(180)," +
                "  `celular` varchar(180) NOT NULL," +
                "  `direccion` varchar(180)," +
                "  `website` varchar(180) NOT NULL," +
                "  `ubicacionLugar` varchar(200) DEFAULT NULL," +
                "  `idTipo_lugar` varchar(11) DEFAULT NULL," +
                "  `persona_email` varchar(256) DEFAULT NULL," +
                "  `id_ciudad` varchar(11) DEFAULT NULL," +
                "  `nombre` varchar(180) NOT NULL," +
                "  `matricula` varchar(64) NOT NULL," +
                "  `enabled` int(10) NOT NULL DEFAULT '1'," +
                "  `locationX` real," +
                "  `locationY` real" +
                ")"
        db?.execSQL(creaateTableLugar)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertLugar(lugar: Lugar){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("idLugar", lugar.id)
        cv.put("email", lugar.email)
        cv.put("descripcion", lugar.descripcion)
        cv.put("barrio", lugar.barrio)
        cv.put("calificacion", lugar.calificacion)
        cv.put("votos", lugar.votos)
        cv.put("telefono", lugar.telefono)
        cv.put("celular", lugar.celular)
        cv.put("direccion", lugar.direccion)
        cv.put("website", lugar.website)
        cv.put("ubicacionLugar", lugar.ubicacion)
        cv.put("idTipo_lugar", lugar.idTipoLugar)
        cv.put("persona_email", lugar.personaEmail)
        cv.put("id_ciudad", lugar.idCiudad)
        cv.put("nombre", lugar.nombre)
        cv.put("matricula", lugar.matricula)
        cv.put("enabled", lugar.habilitado)
        cv.put("locationX", lugar.ubicacionX)
        cv.put("locationY", lugar.ubicacionY)
        val result = db.insert(Constants.DB_TABLE_FAV, null, cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "Error al añadir a favoritos", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Añadido a Favoritos", Toast.LENGTH_SHORT).show()
    }

    fun readDataLugar(): MutableList<Lugar>{
        val list: MutableList<Lugar> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM " + Constants.DB_TABLE_FAV
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
            do {
                val lugar = Lugar()
                lugar.id = result.getString(result.getColumnIndex("idLugar"))
                lugar.email = result.getString(result.getColumnIndex("email"))
                lugar.descripcion = result.getString(result.getColumnIndex("descripcion"))
                lugar.barrio = result.getString(result.getColumnIndex("barrio"))
                lugar.calificacion = result.getDouble(result.getColumnIndex("calificacion"))
                lugar.votos = result.getInt(result.getColumnIndex("votos"))
                lugar.telefono = result.getString(result.getColumnIndex("telefono"))
                lugar.celular = result.getString(result.getColumnIndex("celular"))
                lugar.direccion = result.getString(result.getColumnIndex("direccion"))
                lugar.website = result.getString(result.getColumnIndex("website"))
                lugar.ubicacion = result.getString(result.getColumnIndex("ubicacionLugar"))
                lugar.idTipoLugar= result.getString(result.getColumnIndex("idTipo_lugar"))
                lugar.personaEmail = result.getString(result.getColumnIndex("persona_email"))
                lugar.idCiudad = result.getString(result.getColumnIndex("id_ciudad"))
                lugar.nombre = result.getString(result.getColumnIndex("nombre"))
                lugar.matricula = result.getString(result.getColumnIndex("matricula"))
                lugar.habilitado = result.getInt(result.getColumnIndex("enabled")) > 0
                lugar.ubicacionX = result.getDouble(result.getColumnIndex("locationX"))
                lugar.ubicacionY = result.getDouble(result.getColumnIndex("locationY"))
                list.add(lugar)
            }while (result.moveToNext())
        result.close()
        return  list
    }

    fun deleteLugar(id: String){
        val db = this.writableDatabase
        db.delete(Constants.DB_TABLE_FAV, "idLugar =?", arrayOf(id))
        Toast.makeText(context, "Se ha eliminado de favoritos", Toast.LENGTH_SHORT).show()
        db.close()
    }
}