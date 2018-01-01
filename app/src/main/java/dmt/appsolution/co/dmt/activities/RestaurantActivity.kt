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
import dmt.appsolution.co.dmt.R
import kotlinx.android.synthetic.main.activity_restaurant.*

class RestaurantActivity : AppCompatActivity() , OnMapReadyCallback, OnStreetViewPanoramaReadyCallback {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        mapStreeRestaurant.onCreate(savedInstanceState)
        mapStreeRestaurant.getStreetViewPanoramaAsync(this)
        mapRestaurant.onCreate(savedInstanceState)
        mapRestaurant.getMapAsync(this)
        var arr:MutableList<Int> ?= mutableListOf()
        arr!!.add(R.drawable.tinga_pollo)
        arr.add(R.drawable.tinga_pollo)
        arr.add(R.drawable.tinga_pollo)
        fillCarousel(arr)
    }

    fun fillCarousel(img:MutableList<Int>){
        var carouselPicker:CarouselPicker ?= findViewById(R.id.carouselRestaurant)
        var itemImageCarousel:MutableList<CarouselPicker.PickerItem> ?= mutableListOf()
        for (i in 0..img.size-1){
            itemImageCarousel!!.add(CarouselPicker.DrawableItem(img.get(i)))
        }
        var imageAdapter:CarouselPicker.CarouselViewAdapter = CarouselPicker.CarouselViewAdapter(this, itemImageCarousel, 0)
        carouselPicker!!.adapter=imageAdapter
    }

    override fun onStreetViewPanoramaReady(map: StreetViewPanorama?) {
        map!!.isStreetNamesEnabled = true
        map.setPosition(LatLng(37.765927, -122.449972))
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map!!.isMyLocationEnabled = true
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
