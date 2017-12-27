package dmt.appsolution.co.dmt.fragments

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import dmt.appsolution.co.dmt.adapters.ItemAdapter
import dmt.appsolution.co.dmt.entity.ItemRestaurant
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.dialog.DialogFilter
import dmt.appsolution.co.dmt.entity.Constants
import dmt.appsolution.co.dmt.entity.NoticeDialogListener
import kotlinx.android.synthetic.main.fragment_domicile.*

class DomicileFragment : Fragment(), OnMapReadyCallback, NoticeDialogListener {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_domicile, container, false)

    }
    @SuppressLint("MissingPermission")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startMap(savedInstanceState)
        fillListDomicile()
        buttonFilterDomicile.setOnClickListener{
            var dialog = DialogFilter()
            dialog.show(fragmentManager,tag)
        }
    }

    private fun startMap(savedInstanceState: Bundle?){
        mapViewDomicile.onCreate(savedInstanceState)
        mapViewDomicile.getMapAsync(this)
    }

    private fun fillListDomicile(){
        val items: MutableList<ItemRestaurant> = mutableListOf()
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante Pollo",
                "Asadero", 5, "Todo tipo de pollo", false, "www.labrasaroja.com/",
                "123", "pollo@gmail.com", Constants.CHICKEN_FOOD, Point(100, 100)))
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante Carne",
                "Carnes", 2, "Todo tipo de Carne", false, "www.labrasaroja.com/",
                "123", "pollo@gmail.com", Constants.MEAT_FOOD, Point(100, 100)))
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante Pez",
                "Pescado", 5, "Todo tipo de Pez", false, "www.labrasaroja.com/",
                "123", "pollo@gmail.com", Constants.FISH_FOOD, Point(100, 100)))
        listViewDomicile.adapter = ItemAdapter(this.activity, items)
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

    override fun onAcceptButton() {
        Toast.makeText(context,"Hola", Toast.LENGTH_LONG).show()
    }

    override fun onCancelButton() {
        Toast.makeText(context,"Adios", Toast.LENGTH_LONG).show()
    }
}