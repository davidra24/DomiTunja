package dmt.appsolution.co.dmt.activities

import `in`.goodiebag.carouselpicker.CarouselPicker
import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.entity.ItemRestaurant
import kotlinx.android.synthetic.main.activity_restaurant.*

class RestaurantActivity : AppCompatActivity() , OnMapReadyCallback, OnStreetViewPanoramaReadyCallback {
    private var itemRestaurant: ItemRestaurant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        startMaps(savedInstanceState)
        startCarousel()
        itemRestaurant = intent.extras.getSerializable("Item") as ItemRestaurant
        startComponents()
    }


    private fun startMaps(savedInstanceState: Bundle?) {
        mapStreeRestaurant.onCreate(savedInstanceState)
        mapStreeRestaurant.getStreetViewPanoramaAsync(this)
        mapRestaurant.onCreate(savedInstanceState)
        mapRestaurant.getMapAsync(this)
    }

    private fun startCarousel() {
        var listImg:MutableList<Int> ?= mutableListOf()
        listImg!!.add(R.drawable.tinga_pollo)
        listImg.add(R.drawable.tinga_pollo)
        listImg.add(R.drawable.tinga_pollo)
        fillCarousel(listImg)
    }

    private fun startComponents() {
        txtCategoryName.text = itemRestaurant!!.typeFood
        txtRestaurantName.text = itemRestaurant!!.name
        ratingBarRestauant.rating = itemRestaurant!!.rating.toFloat()
        buttonDescriptionInformation.text = itemRestaurant!!.summary
        buttonNumber.text = itemRestaurant!!.number.toString()
        buttonWebSiteInformation.text = itemRestaurant!!.webSite
        buttonContactInformation.text = itemRestaurant!!.mail
    }

    private fun fillCarousel(img:MutableList<Int>){
        var carouselPicker:CarouselPicker ?= findViewById(R.id.carouselRestaurant)
        var itemImageCarousel:MutableList<CarouselPicker.PickerItem> ?= mutableListOf()
        for (i in 0 until img.size){
            itemImageCarousel!!.add(CarouselPicker.DrawableItem(img.get(i)))
        }
        var imageAdapter:CarouselPicker.CarouselViewAdapter = CarouselPicker.CarouselViewAdapter(this, itemImageCarousel, 0)
        carouselPicker!!.adapter=imageAdapter
    }

    override fun onStreetViewPanoramaReady(map: StreetViewPanorama?) {
        map!!.isStreetNamesEnabled = true
        map.setPosition(LatLng(itemRestaurant!!.locationX, itemRestaurant!!.locationY))
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map!!.isMyLocationEnabled = true
        map.addMarker(MarkerOptions().
                position(LatLng(itemRestaurant!!.locationX, itemRestaurant!!.locationY)).
                title(itemRestaurant!!.name))
    }

    override fun onResume() {
        mapRestaurant.onResume()
        mapStreeRestaurant.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapRestaurant.onDestroy()
        mapStreeRestaurant.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapRestaurant.onLowMemory()
        mapStreeRestaurant.onLowMemory()
    }

}
