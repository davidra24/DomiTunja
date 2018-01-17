package dmt.appsolution.co.dmt.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.constants.Constants
import dmt.appsolution.co.dmt.services.entity.Lugar
import dmt.appsolution.co.dmt.services.entity.TipoLugar

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

    private fun fillTempRestaurants(){
        Constants.restaurantList.add(Lugar(1,"pollo@gmail.com", "Restaurante de pollo", "Las Nieves", 3.5,
                3, "313131313", "300333333", "Calle 24 # 6 -124", "www.labrasaroja.com/",
                "hola K ase", 1, "persona@gmail.com", 34, "Restaurante Pollo",
                "Matricula ", 1, 5.532392303876586, -73.36294144392014))
        Constants.restaurantList.add(Lugar(2,"pollo@gmail.com", "Restaurante de carne", "Las Nieves", 3.5,
                3, "313131313", "300333333", "Calle 24 # 6 -124", "www.labrasaroja.com/",
                "hola K ase", 2, "persona@gmail.com", 34, "Restaurante Carne",
                "Matricula ", 1, 5.532213432951909, -73.36247071623802))
        Constants.restaurantList.add(Lugar(3,"pollo@gmail.com", "Restaurante de pescado", "Las Nieves", 3.5,
                3, "313131313", "300333333", "Calle 24 # 6 -124", "www.labrasaroja.com/",
                "hola K ase", 3, "persona@gmail.com", 34, "Restaurante Pescado",
                "Matricula ", 1, 5.5322694969788975, -73.36216159164906))

        Constants.restaurantType.add(TipoLugar(1, "Pollo"))
        Constants.restaurantType.add(TipoLugar(2, "Carne"))
        Constants.restaurantType.add(TipoLugar(3, "Pescado"))
    }
}
