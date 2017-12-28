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
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.dialog.DialogFilter
import dmt.appsolution.co.dmt.entity.Constants
import kotlinx.android.synthetic.main.fragment_domicile.*

class DomicileFragment : Fragment(), OnMapReadyCallback{
    private var itemAdapter: ItemAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_domicile, container, false)

    }
    @SuppressLint("MissingPermission")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startMap(savedInstanceState)
        filterFood()
        buttonFilterDomicile.setOnClickListener{DialogFilter().show(fragmentManager,tag)}
    }

    private fun startMap(savedInstanceState: Bundle?){
        mapViewDomicile.onCreate(savedInstanceState)
        mapViewDomicile.getMapAsync(this)
    }

    fun filterFood(){
        if (Constants.FOOD_FILTER!=("\"N/N\""))
            Constants.restaurantList.filter { it.typeFood == (Constants.FOOD_FILTER) }
                    .forEach { Constants.filterRestaurantList.add(it) }
        else
            Constants.filterRestaurantList = Constants.restaurantList
        if (itemAdapter == null){
            itemAdapter = ItemAdapter(this.activity, Constants.filterRestaurantList)
            listViewDomicile.adapter = itemAdapter
        }else
            itemAdapter!!.notifyDataSetChanged()

    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map!!.uiSettings.setAllGesturesEnabled(true)
        map.isMyLocationEnabled = true
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
