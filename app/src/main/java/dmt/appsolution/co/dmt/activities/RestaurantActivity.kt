package dmt.appsolution.co.dmt.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback
import com.google.android.gms.maps.StreetViewPanorama
import dmt.appsolution.co.dmt.R
import kotlinx.android.synthetic.main.activity_restaurant.*
import kotlinx.android.synthetic.main.fragment_domicile.*

class RestaurantActivity : AppCompatActivity() , OnMapReadyCallback, OnStreetViewPanoramaReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        mapStreeRestaurant.onCreate(savedInstanceState)
        mapStreeRestaurant.getStreetViewPanoramaAsync(this)
        mapRestaurant.onCreate(savedInstanceState)
        mapRestaurant.getMapAsync(this)
    }

    override fun onStreetViewPanoramaReady(map: StreetViewPanorama?) {
        map!!.isStreetNamesEnabled = true
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map!!.isMyLocationEnabled = true
        map.uiSettings.isZoomControlsEnabled = true
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
