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
        val items: MutableList<ItemRestaurant> = mutableListOf()
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante Pollo",
                "Asadero", 5, "Todo tipo de pollo", false, "www.labrasaroja.com/",
                123, "pollo@gmail.com", Constants.CHICKEN_FOOD, Point(100, 100)))
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante Carne",
                "Carnes", 2, "Todo tipo de Carne", false, "www.labrasaroja.com/",
                123, "pollo@gmail.com", Constants.MEAT_FOOD, Point(100, 100)))
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante Pez",
                "Pescado", 5, "Todo tipo de Pez", false, "www.labrasaroja.com/",
                123, "pollo@gmail.com", Constants.FISH_FOOD, Point(100, 100)))
        listViewFavorite.adapter = ItemAdapter(this.activity, items)
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
