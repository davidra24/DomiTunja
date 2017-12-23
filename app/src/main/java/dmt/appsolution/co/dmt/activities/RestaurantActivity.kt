package dmt.appsolution.co.dmt.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import dmt.appsolution.co.dmt.R
import kotlinx.android.synthetic.main.fragment_domicile.*

class RestaurantActivity : AppCompatActivity() , OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        mapViewDomicile.onCreate(savedInstanceState)
        mapViewDomicile.getMapAsync(this)
    }
    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map!!.isMyLocationEnabled = true
        map.uiSettings.isZoomControlsEnabled = true
    }

    override fun onResume() {
        mapViewDomicile.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapViewDomicile.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapViewDomicile.onLowMemory()
    }

}
