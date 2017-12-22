package dmt.appsolution.co.dmt.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import dmt.appsolution.co.dmt.adapters.ItemAdapter
import dmt.appsolution.co.dmt.itemList.ItemRestaurant
import dmt.appsolution.co.dmt.R
import kotlinx.android.synthetic.main.fragment_domicile.*

class DomicileFragment : Fragment(), OnMapReadyCallback {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_domicile, container, false)

    }
    @SuppressLint("MissingPermission")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fillListDomicile()
        mapViewDomicile.onCreate(savedInstanceState)
        mapViewDomicile.getMapAsync(this)
    }

    private fun fillListDomicile(){
        val items: MutableList<ItemRestaurant> = mutableListOf()
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 5, R.drawable.item_arrow))
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 3, R.drawable.item_arrow))
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 4, R.drawable.item_arrow))
        listViewDomicile.adapter = ItemAdapter(this.activity, items)
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