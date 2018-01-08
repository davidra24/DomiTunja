package dmt.appsolution.co.dmt.entity

import java.io.Serializable

/**
 * Created by Martin on 8/01/2018.
 */
class ItemRestaurant( image: Int, name: String, summary: String, rating: Int, details: String,
                      favorite: Boolean, webSite: String, number: String, mail: String, typeFood: String,
                      locationX: Double, locationY: Double): Serializable {
    var image: Int = image
    var name: String? = name
    var summary: String? = summary
    var details: String? = details
    var webSite: String? = webSite
    var number: String? = number
    var mail: String? = mail
    var typeFood: String? = typeFood
    var rating: Int = rating
    var favorite: Boolean = favorite
    var locationX: Double = locationX
    var locationY: Double = locationY
}