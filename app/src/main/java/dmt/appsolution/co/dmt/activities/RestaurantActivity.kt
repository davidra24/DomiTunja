package dmt.appsolution.co.dmt.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.entity.Constants
import dmt.appsolution.co.dmt.entity.Lugar
import dmt.appsolution.co.dmt.persistence.DataBaseHandler
import kotlinx.android.synthetic.main.activity_restaurant.*

class RestaurantActivity : AppCompatActivity() , OnMapReadyCallback, OnStreetViewPanoramaReadyCallback {
    private var lugar: Lugar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        startMaps(savedInstanceState)
        startCarousel()
        lugar = intent.extras.getSerializable("Item") as Lugar
        startComponents()
        makeACall()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeACall()
            }
        }
    }

    private fun makeACall(){
        //aca se hace la llamada
        try {
        val phoneNumber = lugar!!.celular
            if (phoneNumber!!.isNotEmpty()){
                val url = "tel:$phoneNumber"
                buttonCallInformation.setOnClickListener({
                    val callIntent = Intent(Intent.ACTION_CALL)
                    callIntent.data = Uri.parse(url)
                    if (ContextCompat.checkSelfPermission(this.baseContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.CALL_PHONE), 1)
                    }else
                        startActivity(callIntent)
                })
            }else{
                Toast.makeText(this.baseContext, "??? el restaurante no tiene numero ???",Toast.LENGTH_LONG).show()
            }
        }catch (e:Exception){
            Toast.makeText(this.baseContext, "??? el restaurante no tiene numero ???",Toast.LENGTH_LONG).show()
        }
    }

    private fun startMaps(savedInstanceState: Bundle?) {
        mapStreeRestaurant.onCreate(savedInstanceState)
        mapStreeRestaurant.getStreetViewPanoramaAsync(this)
        mapRestaurant.onCreate(savedInstanceState)
        mapRestaurant.getMapAsync(this)
    }

    private fun startCarousel() {
        val listImg:MutableList<Int> ?= mutableListOf()
        listImg!!.add(R.drawable.tinga_pollo)
        listImg.add(R.drawable.tinga_pollo)
        listImg.add(R.drawable.tinga_pollo)
        fillCarousel(listImg)
    }

    private fun startComponents() {
        Constants.restaurantType.forEach { tipoLugar ->
            if (tipoLugar.idTipoLugar == lugar!!.idtipo_lugar)
                txtCategoryName.text = tipoLugar.tipoLugar
        }
        txtRestaurantName.text = lugar!!.nombre
        ratingBarRestaurant.rating = lugar!!.calificacion.toFloat()
        buttonDescriptionInformation.text = lugar!!.descripcion
        buttonNumber.text = lugar!!.telefono
        buttonWebSiteInformation.text = lugar!!.website
        buttonContactInformation.text = lugar!!.email
        buttonAddFavorite.setOnClickListener {
            validateInsertion()
        }
    }

    private fun validateInsertion() {
        var db = DataBaseHandler(this.baseContext)
        var data: MutableList<Lugar> = db.readDataLugar()
        var isFavorite = false
        for(i in 0 .. (data.size -1)){
            if(data[i].id_lugar == lugar!!.id_lugar)
                isFavorite = true
        }
        if (isFavorite)
            db.deleteLugar(lugar!!.id_lugar)
        else
            db.insertLugar(lugar!!)
        db.close()
    }

    private fun fillCarousel(img:MutableList<Int>){
        val imageListener = ImageListener { position, imageView -> imageView.setImageResource(img[position]) }
        val carouselView:CarouselView ?= findViewById(R.id.carouselRestaurant)
        carouselView!!.pageCount = img.size
        carouselView.setImageListener(imageListener)
    }

    override fun onStreetViewPanoramaReady(map: StreetViewPanorama?) {
        map!!.isStreetNamesEnabled = true
        map.setPosition(LatLng(lugar!!.locationX, lugar!!.locationY))
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map!!.isMyLocationEnabled = true
        map.addMarker(MarkerOptions().
                position(LatLng(lugar!!.locationX, lugar!!.locationY)).
                title(lugar!!.nombre))
    }

    override fun onResume() {
        mapRestaurant.onResume()
        mapStreeRestaurant.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapRestaurant.onDestroy()
        mapStreeRestaurant.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapRestaurant.onLowMemory()
        mapStreeRestaurant.onLowMemory()
    }

}
