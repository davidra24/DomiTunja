package dmt.appsolution.co.dmt.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.adapters.ItemAdapterDomicile
import dmt.appsolution.co.dmt.itemList.ItemDomicile
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
        val items: MutableList<ItemDomicile> = mutableListOf()
        items.add(ItemDomicile(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 5, R.drawable.item_arrow))
        items.add(ItemDomicile(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 3, R.drawable.item_arrow))
        items.add(ItemDomicile(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 4, R.drawable.item_arrow))
        listViewFavorite.adapter = ItemAdapterDomicile(this.context, items)
    }



    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        //map!!.mapType = GoogleMap.MAP_TYPE_HYBRID
        map!!.isMyLocationEnabled = true
        //map.isTrafficEnabled = true
        //map.isIndoorEnabled = true
        //map.isBuildingsEnabled = true
        map.uiSettings.isZoomControlsEnabled = true
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
