package dmt.appsolution.co.dmt.constants
import dmt.appsolution.co.dmt.services.entity.Foto
import dmt.appsolution.co.dmt.services.entity.Lugar
import dmt.appsolution.co.dmt.services.entity.TipoLugar

/**
 * Created by Martin on 27/12/2017.
 */
class Constants {
    companion object {
        var FOOD_FILTER = "0"
        val ALL_FOOD: String = "0"
        val CHICKEN_FOOD: String = "1"
        val MEAT_FOOD: String = "2"
        val FISH_FOOD: String = "3"
        val FACEBOOK_URL: String = "https://www.facebook.com/appsolutionco"
        var restaurantList: MutableList<Lugar> = mutableListOf()
        var filterRestaurantList: MutableList<Lugar> = mutableListOf()
        var restaurantType: MutableList<TipoLugar> = mutableListOf()
        var photoList: MutableList<Foto> = mutableListOf()
        val DB_NAME: String? = "DMT_DB"
        val DB_TABLE_FAV: String? = "LUGARES_FAVORITOS"
        val DB_VERSION: Int = 1
        val REST_URL ="https://domi-rest.herokuapp.com/"
    }
}