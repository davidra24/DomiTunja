package domitunja.appsolution.co.domitunja

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        tabHost.setup()
        tabHost.setOnTabChangedListener(AnimatedTabHostListener(tabHost))
        init()
    }
    private fun init(){
        var spec = tabHost.newTabSpec( "Domicilios")
        spec.setContent(R.id.tab_domicilios)
        spec.setIndicator("Domicilios")
        tabHost.addTab(spec)

        spec = tabHost.newTabSpec( "Recomendados")
        spec.setContent(R.id.tab_recomendados)
        spec.setIndicator("Recomendados")
        tabHost.addTab(spec)

        spec = tabHost.newTabSpec( "Favoritos")
        spec.setContent(R.id.tab_domicilios)
        spec.setIndicator("Favoritos")
        tabHost.addTab(spec)

        spec = tabHost.newTabSpec( "Mas")
        spec.setContent(R.id.tab_domicilios)
        spec.setIndicator("Mas")
        tabHost.addTab(spec)
    }
}