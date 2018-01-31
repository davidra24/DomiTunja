package dmt.appsolution.co.dmt.persistence

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import dmt.appsolution.co.dmt.constants.Constants
import dmt.appsolution.co.dmt.services.entity.LugarDetalles
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Martin on 30/01/2018.
 */
class DataBaseHandlerDetalles(var context: Context): SQLiteOpenHelper(context, Constants.DB_NAME, null, Constants.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val creaateTableLugarDetalles = "CREATE TABLE IF NOT EXISTS " + Constants.DB_TABLE_LUGARES_DETALLES + "(" +
                "  `nombre` varchar(180)," +
                "  `descripcion` varchar(180)," +
                "  `barrio` varchar(180)," +
                "  `email` varchar(180)," +
                "  `calificacion` varchar(180)," +
                "  `votos` varchar(180)," +
                "  `telefono` varchar(180)," +
                "  `celular` varchar(180)," +
                "  `direccion` varchar(180)," +
                "  `website` varchar(180) NOT NULL," +
                "  `ubicacionLugar` varchar(200) DEFAULT NULL," +
                "  `idTipo_lugar` varchar(11) DEFAULT NULL," +
                "  `id_ciudad` varchar(11) DEFAULT NULL," +
                "  `fotos` varchar(180) DEFAULT NULL" +
        ")"
        db?.execSQL(creaateTableLugarDetalles)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertLugarDetalles(lugarDetalles: LugarDetalles){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("nombre", lugarDetalles.nombre)
        cv.put("descripcion", lugarDetalles.descripcion)
        cv.put("barrio", lugarDetalles.barrio)
        cv.put("email", lugarDetalles.email)
        cv.put("calificacion", lugarDetalles.calificacion)
        cv.put("votos", lugarDetalles.votos)
        cv.put("telefono", lugarDetalles.telefono)
        cv.put("celular", lugarDetalles.celular)
        cv.put("direccion", lugarDetalles.direccion)
        cv.put("website", lugarDetalles.website)
        cv.put("ubicacionLugar", lugarDetalles.ubicacionLugar)
        cv.put("idTipo_lugar", lugarDetalles.idTipoLugar)
        cv.put("id_ciudad", lugarDetalles.idCiudad)
        val json = JSONObject()
        json.put("fotos", JSONArray(lugarDetalles.fotos))
        cv.put("fotos", json.toString())
        val result = db.insert(Constants.DB_TABLE_LUGARES_DETALLES, null, cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "Error al añadir a favoritos", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Añadido a Favoritos", Toast.LENGTH_SHORT).show()
    }

    fun readDataLugarDetalles(direccion: String): LugarDetalles? {
        var list: LugarDetalles? = null
        val db = this.readableDatabase
        val query = "SELECT * FROM " + Constants.DB_TABLE_LUGARES_DETALLES + "WHERE `direccion` = '" + direccion+ "'"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
            do {
                val lugarDetalles = LugarDetalles()
                lugarDetalles.nombre = result.getString(result.getColumnIndex("nombre"))
                lugarDetalles.descripcion = result.getString(result.getColumnIndex("descripcion"))
                lugarDetalles.barrio = result.getString(result.getColumnIndex("barrio"))
                lugarDetalles.email = result.getString(result.getColumnIndex("email"))
                lugarDetalles.calificacion = result.getString(result.getColumnIndex("calificacion"))
                lugarDetalles.votos = result.getString(result.getColumnIndex("votos"))
                lugarDetalles.telefono = result.getString(result.getColumnIndex("telefono"))
                lugarDetalles.celular = result.getString(result.getColumnIndex("celular"))
                lugarDetalles.direccion = result.getString(result.getColumnIndex("direccion"))
                lugarDetalles.website = result.getString(result.getColumnIndex("website"))
                lugarDetalles.ubicacionLugar = result.getString(result.getColumnIndex("ubicacionLugar"))
                lugarDetalles.idTipoLugar = result.getString(result.getColumnIndex("idTipo_lugar"))
                lugarDetalles.idCiudad = result.getString(result.getColumnIndex("id_ciudad"))
                val json = JSONObject(result.getString(result.getColumnIndex("fotos")))
                lugarDetalles.fotos = mutableListOf()
                for (i in 0 until json.getJSONArray("fotos").length()) {
                    (lugarDetalles.fotos as MutableList<String>).add((json.getJSONArray("fotos").get(i).toString()))
                }

                list = lugarDetalles
            }while (result.moveToNext())
        result.close()
        return  list
    }

    fun deleteLugarDetalles(direccion: String){
        val db = this.writableDatabase
        db.delete(Constants.DB_TABLE_LUGARES_DETALLES, "direccion =?", arrayOf(direccion))
        Toast.makeText(context, "Se ha eliminado de favoritos", Toast.LENGTH_SHORT).show()
        db.close()
    }
}