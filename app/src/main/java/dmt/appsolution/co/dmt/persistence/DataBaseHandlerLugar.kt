package dmt.appsolution.co.dmt.persistence

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import dmt.appsolution.co.dmt.constants.Constants
import dmt.appsolution.co.dmt.services.entity.Lugar


class DataBaseHandlerLugar(var context: Context): SQLiteOpenHelper (context, Constants.DB_NAME, null, Constants.DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableLugar = "CREATE TABLE IF NOT EXISTS " + Constants.DB_TABLE_LUGARES + "(" +
                "  `idLugar` varchar(11) NOT NULL," +
                "  `nombre` varchar(180)," +
                "  `descripcion` varchar(180)," +
                "  `horario` varchar(100) DEFAULT NULL, " +
                "  `calificacion` varchar(180)," +
                "  `telefono` varchar(180)," +
                "  `direccion` varchar(180)," +
                "  `website` varchar(180) NOT NULL," +
                "  `id_ciudad` varchar(11) DEFAULT NULL," +
                "  `idTipo_lugar` varchar(11) DEFAULT NULL," +
                "  `ubicacionLugar` varchar(200) DEFAULT NULL," +
                "  `url` varchar(100) DEFAULT NULL" +
        ")"
        db?.execSQL(createTableLugar)
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertLugar(lugar: Lugar){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("idLugar", lugar.idLugar)
        cv.put("nombre", lugar.nombre)
        cv.put("descripcion", lugar.descripcion)
        cv.put("horario", lugar.horario)
        cv.put("calificacion", lugar.calificacion)
        cv.put("telefono", lugar.telefono)
        cv.put("direccion", lugar.direccion)
        cv.put("website", lugar.website)
        cv.put("id_ciudad", lugar.idCiudad)
        cv.put("idTipo_lugar", lugar.idTipoLugar)
        cv.put("ubicacionLugar", lugar.ubicacionLugar)
        cv.put("url", lugar.url)
        val result = db.insert(Constants.DB_TABLE_LUGARES, null, cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "Error al añadir a favoritos", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Añadido a Favoritos", Toast.LENGTH_SHORT).show()
    }

    fun readDataLugar(): MutableList<Lugar>{
        val list: MutableList<Lugar> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM " + Constants.DB_TABLE_LUGARES
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
            do {
                val lugar = Lugar()
                lugar.idLugar = result.getString(result.getColumnIndex("idLugar"))
                lugar.nombre = result.getString(result.getColumnIndex("nombre"))
                lugar.descripcion = result.getString(result.getColumnIndex("descripcion"))
                //lugar.horario = result.getString(result.getColumnIndex("horario"))
                lugar.calificacion = result.getString(result.getColumnIndex("calificacion"))
                lugar.telefono = result.getString(result.getColumnIndex("telefono"))
                lugar.direccion = result.getString(result.getColumnIndex("direccion"))
                lugar.website = result.getString(result.getColumnIndex("website"))
                lugar.idCiudad = result.getString(result.getColumnIndex("id_ciudad"))
                lugar.idTipoLugar = result.getString(result.getColumnIndex("idTipo_lugar"))
                lugar.ubicacionLugar = result.getString(result.getColumnIndex("ubicacionLugar"))
                lugar.url = result.getString(result.getColumnIndex("url"))
                list.add(lugar)
            }while (result.moveToNext())
        result.close()
        return  list
    }

    fun deleteLugar(id: String){
        val db = this.writableDatabase
        db.delete(Constants.DB_TABLE_LUGARES, "idLugar =?", arrayOf(id))
        Toast.makeText(context, "Se ha eliminado de favoritos", Toast.LENGTH_SHORT).show()
        db.close()
    }
}