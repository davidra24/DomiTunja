package dmt.appsolution.co.dmt.itemList

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.activities.RestaurantActivity

class ItemDomicileLayout : AppCompatActivity() {

    //val info:ImageButton = R.id.btnViewDomicile as ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_domicile_item)
       // actions()
    }
    /////////Intent aun no lleva a otra actividad
    /*private fun actions(){
        info.setOnClickListener{
            val intent = Intent(this, RestaurantActivity::class.java)
            startActivity(intent)
        }
    }*/
}