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
import dmt.appsolution.co.dmt.constants.Constants
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
        if (Constants.FOOD_FILTER == Constants.ALL_FOOD)
                fillFilterList()
        else {
            Constants.restaurantList.filter { it.idTipoLugar == Constants.FOOD_FILTER }
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
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.ALL_FOOD }
                        .forEach { viewAux!!.textViewTypeFood.text = "Filtrar" }
            }
            Constants.FAST_FOOD -> {
                viewAux!!.buttonFilterDomicile
                        .background = ContextCompat.getDrawable(context, R.drawable.icon5)
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.FAST_FOOD }
                        .forEach { viewAux!!.textViewTypeFood.text = it.tipoLugar }
            }
            Constants.ORIENTAL_FOOD -> {
                viewAux!!.buttonFilterDomicile.background =
                        ContextCompat.getDrawable(context,R.drawable.icon6)
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.ORIENTAL_FOOD }
                        .forEach { viewAux!!.textViewTypeFood.text = it.tipoLugar }
            }
            Constants.ITALIAN_FOOD -> {
                viewAux!!.buttonFilterDomicile.background =
                        ContextCompat.getDrawable(context,R.drawable.icon7)
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.ITALIAN_FOOD}
                        .forEach { viewAux!!.textViewTypeFood.text = it.tipoLugar }
            }
            Constants.TYPICAL_FOOD -> {
                viewAux!!.buttonFilterDomicile
                        .background = ContextCompat.getDrawable(context, R.drawable.icon8)
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.TYPICAL_FOOD}
                        .forEach { viewAux!!.textViewTypeFood.text = it.tipoLugar }
            }
            Constants.DESSERT -> {
                viewAux!!.buttonFilterDomicile.background =
                        ContextCompat.getDrawable(context,R.drawable.icon9)
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.DESSERT}
                        .forEach { viewAux!!.textViewTypeFood.text = it.tipoLugar }
            }
            Constants.BREAKFAST -> {
                viewAux!!.buttonFilterDomicile.background =
                        ContextCompat.getDrawable(context,R.drawable.icon10)
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.BREAKFAST}
                        .forEach { viewAux!!.textViewTypeFood.text = it.tipoLugar }
            }
            Constants.COFFE -> {
                viewAux!!.buttonFilterDomicile
                        .background = ContextCompat.getDrawable(context, R.drawable.icon11)
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.COFFE}
                        .forEach { viewAux!!.textViewTypeFood.text = it.tipoLugar }
            }
            Constants.CHICKEN -> {
                viewAux!!.buttonFilterDomicile.background =
                        ContextCompat.getDrawable(context,R.drawable.icon12)
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.CHICKEN}
                        .forEach { viewAux!!.textViewTypeFood.text = it.tipoLugar }
            }
            Constants.PIZZA -> {
                viewAux!!.buttonFilterDomicile.background =
                        ContextCompat.getDrawable(context,R.drawable.icon13)
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.PIZZA}
                        .forEach { viewAux!!.textViewTypeFood.text = it.tipoLugar }
            }
            Constants.SEA_FOOD -> {
                viewAux!!.buttonFilterDomicile
                        .background = ContextCompat.getDrawable(context, R.drawable.icon14)
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.SEA_FOOD}
                        .forEach { viewAux!!.textViewTypeFood.text = it.tipoLugar }
            }
            Constants.VEGETARIAN_FOOD -> {
                viewAux!!.buttonFilterDomicile.background =
                        ContextCompat.getDrawable(context,R.drawable.icon15)
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.VEGETARIAN_FOOD}
                        .forEach { viewAux!!.textViewTypeFood.text = it.tipoLugar }
            }
            Constants.MEXICAN_FOOD-> {
                viewAux!!.buttonFilterDomicile.background =
                        ContextCompat.getDrawable(context,R.drawable.icon16)
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.MEXICAN_FOOD}
                        .forEach { viewAux!!.textViewTypeFood.text = it.tipoLugar }
            }
            Constants.GOURMET_FOOD-> {
                viewAux!!.buttonFilterDomicile.background =
                        ContextCompat.getDrawable(context,R.drawable.icon4)
                Constants.restaurantType
                        .filter { it.idTipoLugar == Constants.GOURMET_FOOD}
                        .forEach { viewAux!!.textViewTypeFood.text = it.tipoLugar }
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
        Constants.filterRestaurantList
                .filter { it.idLugar != null }
                .forEach {
                    map!!.addMarker(MarkerOptions().position(LatLng(it!!.ubicacionLugar!!.split(",")[0].toDouble(),
                            it!!.ubicacionLugar!!.split(",")[1].toDouble()))
                            .title(it.nombre))
                }
    }

    override fun onResume() {
        mapViewDomicile.onResume()
        filterFood()
        super.onResume()
    }

    override fun onLowMemory() {
        mapViewDomicile.onLowMemory()
        super.onLowMemory()
    }
}
