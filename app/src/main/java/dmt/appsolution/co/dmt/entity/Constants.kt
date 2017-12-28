package dmt.appsolution.co.dmt.entity

/**
 * Created by Martin on 27/12/2017.
 */
class Constants {
    companion object {
        var FOOD_FILTER = "N/N"
        val CHICKEN_FOOD: String = "Pollo"
        val MEAT_FOOD: String = "Carne"
        val FISH_FOOD: String = "Pescado"
        var restaurantList: MutableList<ItemRestaurant> = mutableListOf()
        var filterRestaurantList: MutableList<ItemRestaurant> = mutableListOf()
        var recommendedRestaurantList: MutableList<ItemRestaurant> = mutableListOf()
        var favoriteRestaurantList: MutableList<ItemRestaurant> = mutableListOf()
    }
}