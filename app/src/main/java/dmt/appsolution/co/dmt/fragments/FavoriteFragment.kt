package dmt.appsolution.co.dmt.fragments


import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.adapters.ItemAdapter
import dmt.appsolution.co.dmt.entity.Constants
import dmt.appsolution.co.dmt.entity.ItemRestaurant
import kotlinx.android.synthetic.main.fragment_domicile.*
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment(), OnMapReadyCallback {
    private var itemAdapter: ItemAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,

                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_favorite, container, false)
    }

    @SuppressLint("MissingPermission")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fillListFavorite()
        mapViewFavorite.onCreate(savedInstanceState)
        mapViewFavorite.getMapAsync(this)
    }

    private fun fillListFavorite(){
        Constants.restaurantList.filter { it.isFavorite }.forEach { Constants.favoriteRestaurantList.add(it) }
        itemAdapter = ItemAdapter(this.activity, Constants.favoriteRestaurantList)
        listViewFavorite.adapter = itemAdapter
    }



    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map!!.uiSettings.setAllGesturesEnabled(true)
        map.isMyLocationEnabled = true
    }


    override fun onResume() {
        mapViewFavorite.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapViewFavorite.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapViewFavorite.onLowMemory()
    }
}
