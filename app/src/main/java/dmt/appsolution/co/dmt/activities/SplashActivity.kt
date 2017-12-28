package dmt.appsolution.co.dmt.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.entity.Constants
import dmt.appsolution.co.dmt.entity.ItemRestaurant

class SplashActivity : AppCompatActivity() {
    private val REQUEST_CODE_ASK_PERMISSIONS = 123

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        fillTempRestaurants()
        val hasWriteContactsPermission = ContextCompat.checkSelfPermission(this.applicationContext, "android.permission.ACCESS_FINE_LOCATION")
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf("android.permission.ACCESS_FINE_LOCATION"),
                    REQUEST_CODE_ASK_PERMISSIONS)
        }else
            Handler().postDelayed({
                startActivity(Intent(this, MenuActivity::class.java))
                finish()
            }, 1500)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Handler().postDelayed({
                        startActivity(Intent(this, MenuActivity::class.java))
                        finish()
                    }, 1500)
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }
    }

    fun fillTempRestaurants(){
        Constants.restaurantList.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante Pollo",
                "Asadero", 5, "Todo tipo de pollo", false, "www.labrasaroja.com/",
                123, "pollo@gmail.com", Constants.CHICKEN_FOOD, Point(100, 100)))
        Constants.restaurantList.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante Carne",
                "Carnes", 2, "Todo tipo de Carne", false, "www.labrasaroja.com/",
                123, "pollo@gmail.com", Constants.MEAT_FOOD, Point(100, 100)))
        Constants.restaurantList.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante Pez",
                "Pescado", 5, "Todo tipo de Pez", false, "www.labrasaroja.com/",
                123, "pollo@gmail.com", Constants.FISH_FOOD, Point(100, 100)))
    }
}
