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
import com.bumptech.glide.Glide
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.constants.Constants
import dmt.appsolution.co.dmt.persistence.DataBaseHandlerDetalles
import dmt.appsolution.co.dmt.persistence.DataBaseHandlerLugar
import dmt.appsolution.co.dmt.services.entity.Lugar
import dmt.appsolution.co.dmt.services.entity.LugarDetalles
import kotlinx.android.synthetic.main.activity_restaurant.*

class RestaurantActivity : AppCompatActivity() , OnMapReadyCallback, OnStreetViewPanoramaReadyCallback {
    private var lugarDetalles: LugarDetalles? = null
    private var lugar: Lugar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lugar = intent.extras.getSerializable("Item") as Lugar
        lugarDetalles = Constants.lugarDetalles
        setContentView(R.layout.activity_restaurant)
        startMaps(savedInstanceState)
        makeACall()
        startCarousel()
        startComponents()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeACall()
                }
                return
            }
        }
    }

    private fun makeACall(){
        try {
        var phoneNumber = lugarDetalles!!.celular!!
            if (phoneNumber!!.isNotEmpty())
                call(phoneNumber)
            else {
                phoneNumber = lugarDetalles!!.telefono!!
                if (phoneNumber!!.isNotEmpty())
                        call(phoneNumber)
                else {
                    Toast.makeText(this.baseContext, "El restaurante no tiene numero.", Toast.LENGTH_LONG).show()
                    buttonCallInformation.isEnabled = false
                }
            }
        }catch (e:Exception){
            Toast.makeText(this.baseContext, "??? el restaurante no tiene numero ???",Toast.LENGTH_LONG).show()
            buttonCallInformation.isEnabled = false
        }
    }

    private fun call(phoneNumber: String){
        val url = "tel:$phoneNumber"
        buttonCallInformation.setOnClickListener({
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse(url)
            if (ContextCompat.checkSelfPermission(this.baseContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.CALL_PHONE), 1)
            }else {
                startActivity(callIntent)
                Toast.makeText(baseContext, "Llamando", Toast.LENGTH_SHORT).show()
            }
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
        lugarDetalles!!.fotos!!
                .forEach {listImg.add(Constants.REST_URL + "photos/" + it!!)}
        fillCarousel(listImg)
    }

    private fun fillCarousel(img:MutableList<String>){
        val imageListener = ImageListener {position, imageView -> Glide.with(baseContext).load(img[position]).into(imageView)  }
        val carouselView:CarouselView? = findViewById(R.id.carouselRestaurant)
        carouselView!!.pageCount = img.size
        carouselView.setImageListener(imageListener)
    }

    private fun startComponents() {
        buttonAddFavorite.setOnClickListener {validateInsertion()}
        buttonShare.setOnClickListener {openShareDialog()}
        validateWebButton()
        Constants.restaurantType.forEach { type ->
            if (type.idTipoLugar == lugarDetalles!!.idTipoLugar)txtCategoryName.text = type.tipoLugar}
        txtRestaurantName.text = lugarDetalles!!.nombre
        ratingBarRestaurant.rating = lugarDetalles!!.calificacion!!.toFloat()
        buttonDescriptionInformation.text = lugarDetalles!!.descripcion
        buttonNumber.text = lugarDetalles!!.telefono
        buttonWebSiteInformation.text = lugarDetalles!!.website
        buttonContactInformation.text = lugarDetalles!!.email
    }

    private fun validateWebButton() {
        if(lugarDetalles!!.website != null && lugarDetalles!!.website != "")
            buttonWebSite.setOnClickListener{openWebSite()}
        else
            buttonWebSite.isEnabled = false
    }

    private fun openShareDialog() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        sendIntent.putExtra(Intent.EXTRA_TEXT, lugarDetalles!!.nombre)
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

    private fun openWebSite(){
        val uriUrl = Uri.parse(lugarDetalles!!.website)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }

    private fun validateInsertion() {
        var dbLugares = DataBaseHandlerLugar(this.baseContext)
        var lugares: MutableList<Lugar> = dbLugares.readDataLugar()
        val isFavorite = (0 .. (lugares.size - 1)).any { lugares[it].idLugar == lugar!!.idLugar }
        if (isFavorite)
            dbLugares.deleteLugar(lugar!!.idLugar!!)
        else
            dbLugares.insertLugar(lugar!!)
        dbLugares.close()
    }

    override fun onStreetViewPanoramaReady(map: StreetViewPanorama?) {
        map!!.isStreetNamesEnabled = true
        map.setPosition(LatLng(lugarDetalles!!.ubicacionLugar!!.split(",")[0].toDouble(),
                lugarDetalles!!.ubicacionLugar!!.split(",")[1].toDouble()))
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map!!.isMyLocationEnabled = true
        val latLng = LatLng(lugarDetalles!!.ubicacionLugar!!.split(",")[0].toDouble(),
                lugarDetalles!!.ubicacionLugar!!.split(",")[1].toDouble())
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.toFloat()))
        map.addMarker(MarkerOptions().
                position(latLng).
                title(lugarDetalles!!.nombre))
    }

    override fun onResume() {
        mapRestaurant.onResume()
        mapStreeRestaurant.onResume()
        super.onResume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapRestaurant.onLowMemory()
        mapStreeRestaurant.onLowMemory()
    }

}
