package dmt.appsolution.co.dmt.services

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Created by davic on 7/01/2018.
 */

class VolleySingleton : Application(){
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    var requestQueue:RequestQueue ?= null
    get(){
        if(field == null){
            return Volley.newRequestQueue(applicationContext)
        }
        return field
    }
    fun <T> addToRequestQueue(req:Request <T>){
        req.tag = TAG
        requestQueue?.add(req)
    }
    companion object {
        private val TAG = VolleySingleton::class.java.simpleName
        @get:Synchronized var instance: VolleySingleton ?= null
        private set
    }
}