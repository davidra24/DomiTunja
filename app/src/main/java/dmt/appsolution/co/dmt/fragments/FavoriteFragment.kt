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
import dmt.appsolution.co.dmt.adapters.ItemAdapter
import dmt.appsolution.co.dmt.persistence.DataBaseHandler
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
        var db = DataBaseHandler(this.context)
        itemAdapter = ItemAdapter(this.activity, db.readDataLugar())
        listViewFavorite.adapter = itemAdapter
    }



    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map!!.uiSettings.setAllGesturesEnabled(true)
        map.isMyLocationEnabled = true
    }


    override fun onResume() {
        mapViewFavorite.onResume()
        fillListFavorite()
        super.onResume()
    }

    override fun onDestroy() {
        mapViewFavorite.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        mapViewFavorite.onLowMemory()
        super.onLowMemory()
    }
}
