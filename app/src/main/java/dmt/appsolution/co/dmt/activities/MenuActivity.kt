package dmt.appsolution.co.dmt.activities
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.TabHost
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.adapters.FragmentAdapter
import dmt.appsolution.co.dmt.entity.NoticeDialogListener
import dmt.appsolution.co.dmt.fragments.DomicileFragment
import dmt.appsolution.co.dmt.fragments.FavoriteFragment
import dmt.appsolution.co.dmt.fragments.RecommendedFragment
import dmt.appsolution.co.dmt.fragments.MoreFragment
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener, NoticeDialogListener {
    private val listF: MutableList<android.support.v4.app.Fragment> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        tabHost.setup()
        initVIewPager()
        initTabHost()
    }

    private fun initVIewPager(){
        listF.add(DomicileFragment())
        listF.add(RecommendedFragment())
        listF.add(FavoriteFragment())
        listF.add(MoreFragment())
        val fragmentAdapter = FragmentAdapter(this.supportFragmentManager, listF)
        viewPagerMenu.adapter = fragmentAdapter
        viewPagerMenu.addOnPageChangeListener(this)
    }

    private fun initTabHost() {
        val tabNames = arrayOf(baseContext.getString(R.string.domicile), baseContext.getString(R.string.recomended),
                baseContext.getString(R.string.favorites), baseContext.getString(R.string.more))
        var spec: TabHost.TabSpec?
        for (tab in tabNames){
            spec = tabHost.newTabSpec(tab.toString())
            spec.setIndicator(tab.toString())
            spec.setContent(FakeContent(this))
            tabHost.addTab(spec)
        }
        tabHost.setOnTabChangedListener(this)
    }

    override fun onTabChanged(p0: String?) {
        viewPagerMenu.currentItem = tabHost.currentTab
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        horizontalScrollViewMenu.smoothScrollTo(tabHost.width * (position / (3 - 1)), 0)
        tabHost.currentTab = position
    }

    inner class FakeContent(context: Context) : TabHost.TabContentFactory {
        var context: Context? = null
        init {
            this.context = context
        }
        override fun createTabContent(p0: String?): View {
            val fakeView = View(context)
            fakeView.minimumHeight = 0
            fakeView.minimumWidth = 0
            return  fakeView
        }
    }

    override fun onAcceptButton() {
        var domicileFragment: DomicileFragment = listF[0] as DomicileFragment
        domicileFragment.filterFood()
    }

}
