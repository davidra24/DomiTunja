package dmt.appsolution.co.dmt.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.services.consumeRest.DetailsRest

class LoadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailsRest(this, intent.extras).execute()
        setContentView(R.layout.activity_load)
    }
}
