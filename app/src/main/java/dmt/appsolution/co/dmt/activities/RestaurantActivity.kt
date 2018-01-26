package dmt.appsolution.co.dmt.activities
import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawableResource
import com.bumptech.glide.load.resource.drawable.DrawableResource
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.constants.Constants
import dmt.appsolution.co.dmt.persistence.DataBaseHandler
import dmt.appsolution.co.dmt.services.consumeRest.DomiRest
import dmt.appsolution.co.dmt.services.entity.Lugar
import kotlinx.android.synthetic.main.activity_restaurant.*
import java.util.ArrayList

class RestaurantActivity : AppCompatActivity() , OnMapReadyCallback, OnStreetViewPanoramaReadyCallback {
    private var lugar: Lugar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lugar = intent.extras.getSerializable("Item") as Lugar
        setContentView(R.layout.activity_restaurant)
        startMaps(savedInstanceState)
        makeACall()
        startCarousel()
        startComponents()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeACall()
            }
        }
    }

    private fun makeACall(){
        try {
        var phoneNumber = lugar!!.celular!!
            if (phoneNumber!!.isNotEmpty())
                call(phoneNumber)
            else {
                phoneNumber = lugar!!.telefono!!
                if (phoneNumber!!.isNotEmpty())
                        call(phoneNumber)
                else
                    Toast.makeText(this.baseContext, "El restaurante no tiene numero.",Toast.LENGTH_LONG).show()
            }
        }catch (e:Exception){
            Toast.makeText(this.baseContext, "??? el restaurante no tiene numero ???",Toast.LENGTH_LONG).show()
        }
    }

    private fun call(phoneNumber: String){
        val url = "tel:$phoneNumber"
        buttonCallInformation.setOnClickListener({
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse(url)
            if (ContextCompat.checkSelfPermission(this.baseContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.CALL_PHONE), 1)
            }else
                startActivity(callIntent)
        })
    }

    private fun startMaps(savedInstanceState: Bundle?) {
        mapStreeRestaurant.onCreate(savedInstanceState)
        mapStreeRestaurant.getStreetViewPanoramaAsync(this)
        mapRestaurant.onCreate(savedInstanceState)
        mapRestaurant.getMapAsync(this)
    }

    private fun startCarousel() {
        val listImg:MutableList<String> = mutableListOf()
        Constants.photoList
                .filter { lugar!!.id == it.idLugar }
                .forEach {listImg.add(it.url!!)}
        fillCarousel(listImg)
    }

    private fun fillCarousel(img:MutableList<String>){
        val imageListener = ImageListener {position, imageView -> Glide.with(baseContext).load(img[position]).into(imageView)  }
        val carouselView:CarouselView ?= findViewById(R.id.carouselRestaurant)
        carouselView!!.pageCount = img.size
        carouselView.setImageListener(imageListener)
    }

    private fun startComponents() {
        buttonAddFavorite.setOnClickListener {validateInsertion()}
        buttonShare.setOnClickListener {openShareDialog()}
        buttonWebSite.setOnClickListener{openWebSite()}
        Constants.restaurantType.forEach { name ->if (name.id == lugar!!.idTipoLugar)txtCategoryName.text = name.name}
        txtRestaurantName.text = lugar!!.nombre
        ratingBarRestaurant.rating = lugar!!.calificacion!!.toFloat()
        buttonDescriptionInformation.text = lugar!!.descripcion
        buttonNumber.text = lugar!!.telefono
        buttonWebSiteInformation.text = lugar!!.website
        buttonContactInformation.text = lugar!!.email
    }

    private fun openShareDialog() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, lugar!!.nombre)
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

    private fun openWebSite(){
        val uriUrl = Uri.parse("https://" + lugar!!.website)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }

    private fun validateInsertion() {
        var db = DataBaseHandler(this.baseContext)
        var data: MutableList<Lugar> = db.readDataLugar()
        val isFavorite = (0 .. (data.size -1)).any { data[it].id == lugar!!.id }
        if (isFavorite)
            db.deleteLugar(lugar!!.id!!)
        else
            db.insertLugar(lugar!!)
        db.close()
    }

    override fun onStreetViewPanoramaReady(map: StreetViewPanorama?) {
        map!!.isStreetNamesEnabled = true
        map.setPosition(LatLng(lugar!!.ubicacionX!!, lugar!!.ubicacionY!!))
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map!!.isMyLocationEnabled = true
        map.addMarker(MarkerOptions().
                position(LatLng(lugar!!.ubicacionX!!, lugar!!.ubicacionY!!)).
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
