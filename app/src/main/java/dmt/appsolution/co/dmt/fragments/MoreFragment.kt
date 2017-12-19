package dmt.appsolution.co.dmt.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dmt.appsolution.co.dmt.R
import kotlinx.android.synthetic.main.fragment_more.*


class MoreFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_more, container, false)
    }

}
