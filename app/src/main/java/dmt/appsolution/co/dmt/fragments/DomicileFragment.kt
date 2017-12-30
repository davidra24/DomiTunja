package dmt.appsolution.co.dmt.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
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
        validateAdapter()
        setButtonImgFilter()
        buttonFilterDomicile.setOnClickListener{DialogFilter().show(fragmentManager,tag)}
    }


    private fun startMap(savedInstanceState: Bundle?){
        mapViewDomicile.onCreate(savedInstanceState)
        mapViewDomicile.getMapAsync(this)
    }

    fun filterFood(){
        Constants.filterRestaurantList.clear()
        if (Constants.FOOD_FILTER == "Todo")
                fillFilterList()
        else
            Constants.restaurantList.filter { it.typeFood == Constants.FOOD_FILTER }
                    .forEach { Constants.filterRestaurantList.add(it) }
    }

    private fun fillFilterList() {
        for (restaurant in Constants.restaurantList)
            Constants.filterRestaurantList.add(restaurant)
    }

    private fun validateAdapter() {
        if (itemAdapter == null) {
            this.itemAdapter = ItemAdapter(activity, Constants.filterRestaurantList)
            listViewDomicile.adapter = this.itemAdapter
        }else
            this.itemAdapter!!.notifyDataSetChanged()
    }

    private fun setButtonImgFilter() {
        when(Constants.FOOD_FILTER){
            Constants.CHICKEN_FOOD -> buttonFilterDomicile.background = ContextCompat.getDrawable(context, R.drawable.icon12)
            Constants.MEAT_FOOD -> buttonFilterDomicile.background = ContextCompat.getDrawable(context,R.drawable.icon16)
            Constants.FISH_FOOD -> buttonFilterDomicile.background = ContextCompat.getDrawable(context,R.drawable.icon14)
        }

    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map!!.uiSettings.setAllGesturesEnabled(true)
        map.isMyLocationEnabled = true
        for(restaurant in Constants.filterRestaurantList)
            map.addMarker(MarkerOptions().
                    position(LatLng(restaurant.locationX, restaurant.locationY))
                    .title(restaurant.name))
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
