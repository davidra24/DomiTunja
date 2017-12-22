package dmt.appsolution.co.dmt.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.adapters.MoreAdapter
import dmt.appsolution.co.dmt.itemList.ItemMore
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_more.*


class MoreFragment : Fragment() {

    /*
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater!!.inflate(R.layout.fragment_more, container, false)
    }*/
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val recycleMore = inflater!!.inflate(R.layout.fragment_more,container,false) as RecyclerView
        setupRecyclerView(recycleMore)
        return inflater!!.inflate(R.layout.fragment_more, container, false)
    }

    private fun setupRecyclerView(recycleMore: RecyclerView){
        recycleMore.layoutManager = LinearLayoutManager(activity)
        val adapterMore = MoreAdapter(createMoreItemList())
        recycleMore.adapter = adapterMore

    }

    private fun createMoreItemList(): ArrayList<ItemMore> {
        val imegList = ArrayList<ItemMore>()
        imegList.add(ItemMore(R.drawable.traffic))
        return imegList
    }
    /*
    private fun initMoreTab(){

        val recycleMore= findViewById<RecyclerView>(R.id.moreRecyclerViewId) as RecyclerView
        recycleMore.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        val moreAdapter = MoreAdapter(imegList)
        recycleMore.adapter = moreAdapter
    }*/

}
