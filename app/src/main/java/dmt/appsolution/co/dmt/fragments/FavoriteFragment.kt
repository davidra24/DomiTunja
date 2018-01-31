package dmt.appsolution.co.dmt.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.adapters.ItemAdapter
import dmt.appsolution.co.dmt.persistence.DataBaseHandlerLugar
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment(), OnMapReadyCallback {
    private var itemAdapter: ItemAdapter? = null
    private var map: GoogleMap? = null

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
        var db = DataBaseHandlerLugar(this.context)
        itemAdapter = ItemAdapter(this.activity, db.readDataLugar())
        listViewFavorite.adapter = itemAdapter
        db.close()
        if(map != null) {
            map!!.clear()
            addMarkers(map)
        }
    }



    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map!!.uiSettings.setAllGesturesEnabled(true)
        map.isMyLocationEnabled = true
        this.map = map
        addMarkers(map)
    }

    private fun addMarkers(map: GoogleMap?){
        var db = DataBaseHandlerLugar(this.context)
        db.readDataLugar()
                .filter { it.idLugar != null }
                .forEach {
                    map!!.addMarker(MarkerOptions().position(LatLng(it!!.ubicacionLugar!!.split(",")[0].toDouble(),
                            it!!.ubicacionLugar!!.split(",")[1].toDouble()))
                            .title(it.nombre))
                }
        db.close()
    }


    override fun onResume() {
        mapViewFavorite.onResume()
        fillListFavorite()
        super.onResume()
    }

    override fun onLowMemory() {
        mapViewFavorite.onLowMemory()
        super.onLowMemory()
    }
}
