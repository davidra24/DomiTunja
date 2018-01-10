package dmt.appsolution.co.dmt.entity

/**
 * Created by Martin on 27/12/2017.
 */
class Constants {
    companion object {
        var FOOD_FILTER = "Todo"
        val ALL_FOOD: String = "Todo"
        val CHICKEN_FOOD: String = "Pollo"
        val MEAT_FOOD: String = "Carne"
        val FISH_FOOD: String = "Pescado"
        val DEFAULT_PHONE_NUMBER: String = "3191231234"
        val FACEBOOK_URL: String = "https://www.facebook.com/appsolutionco"
        val FACEBOOK_PAGE_ID: String = "https://www.facebook.com/appsolutionco"
        var restaurantList: MutableList<ItemRestaurant> = mutableListOf()
        var filterRestaurantList: MutableList<ItemRestaurant> = mutableListOf()
        var recommendedRestaurantList: MutableList<ItemRestaurant> = mutableListOf()
        var favoriteRestaurantList: MutableList<ItemRestaurant> = mutableListOf()
        val DB_NAME: String = "DMT_DB"
        val DB_VERSION: Int = 1

    }
}