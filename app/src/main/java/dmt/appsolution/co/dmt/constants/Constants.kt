package dmt.appsolution.co.dmt.constants
import dmt.appsolution.co.dmt.services.entity.Lugar
import dmt.appsolution.co.dmt.services.entity.LugarDetalles
import dmt.appsolution.co.dmt.services.entity.TipoLugar

/**
 * Created by Martin on 27/12/2017.
 */
class Constants {
    companion object {
        var FOOD_FILTER = "0"
        val ALL_FOOD: String = "0"
        val FAST_FOOD: String = "5"
        val ORIENTAL_FOOD: String = "6"
        val ITALIAN_FOOD: String = "7"
        val TYPICAL_FOOD: String = "8"
        val DESSERT: String = "9"
        val BREAKFAST: String = "10"
        val COFFE: String = "11"
        val CHICKEN: String = "12"
        val PIZZA: String = "13"
        val SEA_FOOD: String = "14"
        val VEGETARIAN_FOOD: String = "15"
        val MEXICAN_FOOD: String = "16"
        val GOURMET_FOOD: String = "17"
        val FACEBOOK_URL: String = "https://www.facebook.com/appsolutionco"
        var restaurantList: MutableList<Lugar> = mutableListOf()
        var filterRestaurantList: MutableList<Lugar> = mutableListOf()
        var restaurantType: MutableList<TipoLugar> = mutableListOf()
        val DB_NAME: String? = "DMT_DB"
        val DB_TABLE_LUGARES: String? = "LUGARES_FAVORITOS"
        val DB_TABLE_LUGARES_DETALLES: String? = "LUGARES_DETALLES_FAVORITOS"
        val DB_VERSION: Int = 1
        val REST_URL ="http://domitunja.appsolution.co/"
        var ID_LUGAR = ""
        var lugarDetalles: LugarDetalles? = null
    }
}