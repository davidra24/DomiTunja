package dmt.appsolution.co.dmt.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dmt.appsolution.co.dmt.adapters.ItemAdapter
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.dialog.DialogFilter
import dmt.appsolution.co.dmt.entity.Constants
import kotlinx.android.synthetic.main.fragment_domicile.*
import kotlinx.android.synthetic.main.fragment_domicile.view.*

class DomicileFragment : Fragment(), OnMapReadyCallback{
    private var itemAdapter: ItemAdapter? = null
    private var viewAux: View? = null
    private var map: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewAux = inflater!!.inflate(R.layout.fragment_domicile, container, false)
        startMap(savedInstanceState)
        filterFood()
        viewAux!!.buttonFilterDomicile.setOnClickListener{DialogFilter().show(fragmentManager,tag)}
        return viewAux

    }

    private fun startMap(savedInstanceState: Bundle?){
        viewAux!!.mapViewDomicile.onCreate(savedInstanceState)
        viewAux!!.mapViewDomicile.getMapAsync(this)
    }

    fun filterFood(){
        Constants.filterRestaurantList.clear()
        if (Constants.FOOD_FILTER == "Todo")
                fillFilterList()
        else {
            Constants.restaurantList.filter { it.typeFood == Constants.FOOD_FILTER }
                    .forEach { Constants.filterRestaurantList.add(it) }
        }
        validateAdapter()
        setButtonImgFilter()
        if(map != null) {
            map!!.clear()
            addMarkers(map)
        }
    }

    private fun fillFilterList() {
        for (restaurant in Constants.restaurantList)
            Constants.filterRestaurantList.add(restaurant)
    }

    private fun validateAdapter() {
        itemAdapter = ItemAdapter(activity, Constants.filterRestaurantList)
        viewAux!!.listViewDomicile.adapter = itemAdapter
    }

    private fun setButtonImgFilter() {
        when(Constants.FOOD_FILTER){
            Constants.ALL_FOOD -> {
                viewAux!!.buttonFilterDomicile
                        .background = ContextCompat.getDrawable(context, R.drawable.icon4)
                viewAux!!.textViewTypeFood.text = Constants.ALL_FOOD
            }
            Constants.CHICKEN_FOOD -> {
                viewAux!!.buttonFilterDomicile
                        .background = ContextCompat.getDrawable(context, R.drawable.icon12)
                viewAux!!.textViewTypeFood.text = Constants.CHICKEN_FOOD
            }
            Constants.MEAT_FOOD -> {
                viewAux!!.buttonFilterDomicile.background =
                        ContextCompat.getDrawable(context,R.drawable.icon16)
                viewAux!!.textViewTypeFood.text = Constants.MEAT_FOOD
            }
            Constants.FISH_FOOD -> {
                viewAux!!.buttonFilterDomicile.background =
                        ContextCompat.getDrawable(context,R.drawable.icon14)
                viewAux!!.textViewTypeFood.text = Constants.FISH_FOOD
            }
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
        for(restaurant in Constants.filterRestaurantList)
            map!!.addMarker(MarkerOptions().
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
        mapViewDomicile.onLowMemory()
        super.onLowMemory()
    }
}
