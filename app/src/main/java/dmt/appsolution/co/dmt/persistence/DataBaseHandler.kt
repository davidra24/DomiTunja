package dmt.appsolution.co.dmt.persistence

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.ArrayAdapter
import android.widget.Toast
import dmt.appsolution.co.dmt.entity.Constants
import dmt.appsolution.co.dmt.entity.Lugar

/**
 * Created by Martin on 8/01/2018.
 */
class DataBaseHandler(var context: Context): SQLiteOpenHelper (context, Constants.DB_NAME, null, Constants.DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        val creaateTableLugar = "CREATE TABLE IF NOT EXISTS " + Constants.DB_TABLE_FAV + "(" +
                "  `idLugar` int(11) NOT NULL," +
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
                "  `idTipo_lugar` int(11) NOT NULL," +
                "  `persona_email` varchar(256) NOT NULL," +
                "  `id_ciudad` int(11) NOT NULL," +
                "  `nombre` varchar(180) NOT NULL," +
                "  `matricula` varchar(64) NOT NULL," +
                "  `enabled` int(10) NOT NULL DEFAULT '1'," +
                "  `locationX` real," +
                "  `locationY` real" +
                ")"
        db?.execSQL(creaateTableLugar)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertLugar(lugar: Lugar){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put("idLugar", lugar.id_lugar)
        cv.put("email", lugar.email)
        cv.put("descripcion", lugar.descripcion)
        cv.put("barrio", lugar.barrio)
        cv.put("calificacion", lugar.calificacion)
        cv.put("votos", lugar.votos)
        cv.put("telefono", lugar.telefono)
        cv.put("celular", lugar.celular)
        cv.put("direccion", lugar.direccion)
        cv.put("website", lugar.website)
        cv.put("ubicacionLugar", lugar.ubicacionlugar)
        cv.put("idTipo_lugar", lugar.idtipo_lugar)
        cv.put("persona_email", lugar.persona_email)
        cv.put("id_ciudad", lugar.id_ciudad)
        cv.put("nombre", lugar.nombre)
        cv.put("matricula", lugar.matricula)
        cv.put("enabled", lugar.enabled)
        cv.put("locationX", lugar.locationX)
        cv.put("locationY", lugar.locationY)
        var result = db.insert(Constants.DB_TABLE_FAV, null, cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "Error al añadir a favoritos", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Añadido a Favoritos", Toast.LENGTH_SHORT).show()
    }

    fun readDataLugar(): MutableList<Lugar>{
        var list: MutableList<Lugar> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM " + Constants.DB_TABLE_FAV
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
            do {
                var lugar = Lugar()
                lugar.id_lugar = result.getInt(result.getColumnIndex("idLugar"))
                lugar.email = result.getString(result.getColumnIndex("email"))
                lugar.descripcion = result.getString(result.getColumnIndex("descripcion"))
                lugar.barrio = result.getString(result.getColumnIndex("barrio"))
                lugar.calificacion = result.getDouble(result.getColumnIndex("calificacion"))
                lugar.votos = result.getInt(result.getColumnIndex("votos"))
                lugar.telefono = result.getString(result.getColumnIndex("telefono"))
                lugar.celular = result.getString(result.getColumnIndex("celular"))
                lugar.direccion = result.getString(result.getColumnIndex("direccion"))
                lugar.website = result.getString(result.getColumnIndex("website"))
                lugar.ubicacionlugar = result.getString(result.getColumnIndex("ubicacionLugar"))
                lugar.idtipo_lugar = result.getInt(result.getColumnIndex("idTipo_lugar"))
                lugar.persona_email = result.getString(result.getColumnIndex("persona_email"))
                lugar.id_ciudad = result.getInt(result.getColumnIndex("id_ciudad"))
                lugar.nombre = result.getString(result.getColumnIndex("nombre"))
                lugar.matricula = result.getString(result.getColumnIndex("matricula"))
                lugar.enabled = result.getInt(result.getColumnIndex("enabled"))
                lugar.locationX = result.getDouble(result.getColumnIndex("locationX"))
                lugar.locationY = result.getDouble(result.getColumnIndex("locationY"))
                list.add(lugar)
            }while (result.moveToNext())
        result.close()
        return  list
    }

    fun deleteLugar(id: Int){
        val db = this.writableDatabase
        db.delete(Constants.DB_TABLE_FAV, "idLugar =?", arrayOf(id.toString()))
        Toast.makeText(context, "Se ha eliminado de favoritos", Toast.LENGTH_SHORT).show()
        db.close()
    }
}