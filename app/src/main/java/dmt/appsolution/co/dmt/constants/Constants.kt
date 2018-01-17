package dmt.appsolution.co.dmt.constants
import dmt.appsolution.co.dmt.services.entity.Lugar
import dmt.appsolution.co.dmt.services.entity.TipoLugar

/**
 * Created by Martin on 27/12/2017.
 */
class Constants {
    companion object {
        var FOOD_FILTER = 0
        val ALL_FOOD: Int = 0
        val CHICKEN_FOOD: Int = 1
        val MEAT_FOOD: Int = 2
        val FISH_FOOD: Int = 3
        val FACEBOOK_URL: String = "https://www.facebook.com/appsolutionco"
        var restaurantList: MutableList<Lugar> = mutableListOf()
        var filterRestaurantList: MutableList<Lugar> = mutableListOf()
        var recommendedRestaurantList: MutableList<Lugar> = mutableListOf()
        var favoriteRestaurantList: MutableList<Lugar> = mutableListOf()
        var restaurantType: MutableList<TipoLugar> = mutableListOf()
        val DB_NAME: String? = "DMT_DB"
        val DB_TABLE_FAV: String? = "LUGARES_FAVORITOS"
        val DB_VERSION: Int = 1
        val REST_URL ="https://sw-news-letter.herokuapp.com/"
    }
}