package dmt.appsolution.co.dmt.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmt.appsolution.co.dmt.R

class DomicileFragment:Fragment() {
     override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                      savedInstanceState: Bundle?): View? {
         // Inflate the layout for this fragment
         return inflater!!.inflate(R.layout.fragment_domicile, container, false)
     }
 }