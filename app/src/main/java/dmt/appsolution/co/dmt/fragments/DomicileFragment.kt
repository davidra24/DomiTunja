package dmt.appsolution.co.dmt.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import dmt.appsolution.co.dmt.ItemList.ItemAdapterDomicile
import dmt.appsolution.co.dmt.ItemList.ItemDomicile
import dmt.appsolution.co.dmt.ItemList.ItemDomicileLayout
import kotlinx.android.synthetic.main.fragment_domicile.view.*
import kotlinx.android.synthetic.main.fragment_favorite.*
import java.util.ArrayList

class DomicileFragment : Fragment(), OnMapReadyCallback {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,

                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_domicile, container, false)
    }
    @SuppressLint("MissingPermission")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapViewFavorite.onCreate(savedInstanceState)
        mapViewFavorite.getMapAsync(this)
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
    fun fillList(){
        val lista: ListView
        val items: MutableList<ItemDomicile>
        lista = findViewById(R.id.listViewDomicile) as ListView
        items = ArrayList<ItemDomicile>()
        items.add(ItemDomicile(R.drawable.add_favoritos_icon, "Restaurante", "Cra 12 #34-55", arrayListOf(R.drawable.star_full,R.drawable.star_full,R.drawable.star_full,R.drawable.star_empty,R.drawable.star_empty), R.drawable.item_arrow))
        items.add(ItemDomicile(R.drawable.add_favoritos_icon, "Restaurante", "Cra 12 #34-55", arrayListOf(R.drawable.star_full,R.drawable.star_full,R.drawable.star_full,R.drawable.star_empty,R.drawable.star_empty), R.drawable.item_arrow))
        items.add(ItemDomicile(R.drawable.add_favoritos_icon, "Restaurante", "Cra 12 #34-55", arrayListOf(R.drawable.star_full,R.drawable.star_full,R.drawable.star_full,R.drawable.star_empty,R.drawable.star_empty), R.drawable.item_arrow))
        lista.adapter = ItemAdapterDomicile(this, items)
    }
}