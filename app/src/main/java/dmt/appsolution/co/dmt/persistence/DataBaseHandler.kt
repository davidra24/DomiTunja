package dmt.appsolution.co.dmt.persistence

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dmt.appsolution.co.dmt.entity.Constants
import dmt.appsolution.co.dmt.entity.ItemRestaurant

/**
 * Created by Martin on 7/01/2018.
 */
class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, Constants.DB_NAME, null, Constants.DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        val table = "CREATE TABLE IF NOT EXISTS `lugar` (" +
                "  `idLugar` int(11) NOT NULL," +
                "  `email` text," +
                "  `descripcion` text," +
                "  `barrio` varchar(340) DEFAULT NULL," +
                "  `calificacion` float DEFAULT '0'," +
                "  `votos` int(11) NOT NULL DEFAULT '3'," +
                "  `telefono` text," +
                "  `celular` varchar(180) NOT NULL," +
                "  `direccion` text," +
                "  `website` text NOT NULL," +
                "  `ubicacionLugar` varchar(200) DEFAULT NULL," +
                "  `idTipo_lugar` int(11) NOT NULL," +
                "  `persona_email` varchar(256) NOT NULL," +
                "  `id_ciudad` int(11) NOT NULL," +
                "  `nombre` text NOT NULL," +
                "  `matricula` varchar(64) NOT NULL," +
                "  `enabled` int(10) NOT NULL DEFAULT '1'" +
                ") "
        db?.execSQL(table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertDataLugar(itemRestaurant: ItemRestaurant){
        val db = this.writableDatabase
        var contentValues = ContentValues()
        //contentValues.put()
    }
}