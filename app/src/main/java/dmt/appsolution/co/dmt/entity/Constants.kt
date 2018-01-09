package dmt.appsolution.co.dmt.entity

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
        val DEFAULT_PHONE_NUMBER: String = "3191231234"
        var restaurantList: MutableList<Lugar> = mutableListOf()
        var filterRestaurantList: MutableList<Lugar> = mutableListOf()
        var recommendedRestaurantList: MutableList<Lugar> = mutableListOf()
        var favoriteRestaurantList: MutableList<Lugar> = mutableListOf()
        var restaurantType: MutableList<TipoLugar> = mutableListOf()
        val DB_NAME: String? = "DMT_DB"
        val DB_TABLE_FAV: String? = "LUGARES_FAVORITOS"
        val DB_VERSION: Int = 1

    }
}