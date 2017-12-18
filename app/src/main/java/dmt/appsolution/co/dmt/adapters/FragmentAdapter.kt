package dmt.appsolution.co.dmt.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FragmentAdapter(fm: FragmentManager?, listFragment: MutableList<Fragment>) : FragmentPagerAdapter(fm) {
    var listFragment: MutableList<Fragment>? = null
    init {
        this.listFragment = listFragment
    }

    override fun getItem(position: Int): Fragment {
        return listFragment!![position]
    }

    override fun getCount(): Int {
        return listFragment!!.size
    }
}