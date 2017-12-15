package domitunja.appsolution.co.domitunja

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        Handler().postDelayed({
                startActivity(Intent(this, MenuActivity::class.java))
                finish()
        }, 1500)
    }
}
