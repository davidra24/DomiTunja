package dmt.appsolution.co.dmt.activities

import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.constants.Constants
import dmt.appsolution.co.dmt.services.consumeRest.DomiRest
import dmt.appsolution.co.dmt.services.entity.Lugar
import dmt.appsolution.co.dmt.services.interfaces.LugarInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SplashActivity : AppCompatActivity() {
    private val REQUEST_CODE_ASK_PERMISSIONS = 123
    private var mCompositeDisposable: CompositeDisposable? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val hasWriteContactsPermission = ContextCompat.checkSelfPermission(this.applicationContext, "android.permission.ACCESS_FINE_LOCATION")
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf("android.permission.ACCESS_FINE_LOCATION"),
                    REQUEST_CODE_ASK_PERMISSIONS)
        }else
            fillTempRestaurants()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fillTempRestaurants()
                } else {
                    Process.killProcess(Process.myPid())
                }
                return
            }
        }
    }

    private fun fillTempRestaurants(){
        val domiRest = DomiRest(this)
        domiRest.execute()
    }


}
