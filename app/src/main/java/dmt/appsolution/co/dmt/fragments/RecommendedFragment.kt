package dmt.appsolution.co.dmt.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.adapters.ItemAdapter
import dmt.appsolution.co.dmt.itemList.ItemRestaurant
import kotlinx.android.synthetic.main.fragment_recomended.*


class RecommendedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_recomended, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fillListRecommended()
       // setupRecyclerView()
    }

    private fun fillListRecommended(){
        val items: MutableList<ItemRestaurant> = mutableListOf()
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 5, R.drawable.item_arrow))
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 3, R.drawable.item_arrow))
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 4, R.drawable.item_arrow))
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 4, R.drawable.item_arrow))
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 4, R.drawable.item_arrow))
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 4, R.drawable.item_arrow))
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 4, R.drawable.item_arrow))
        items.add(ItemRestaurant(R.drawable.photo_apartament, "Restaurante",
                "Cra 12 #34-55", 4, R.drawable.item_arrow))
        listViewRecommended.adapter = ItemAdapter(this.activity, items)
    }

    /*private fun setupRecyclerView(){
        recyclerMore.layoutManager = LinearLayoutManager(activity)
        val adapterMore = RecomendedAdapter(createMoreItemList())
        recyclerMore.adapter = adapterMore

    }

    private fun createMoreItemList(): ArrayList<ItemRecommended> {
        val imegList = ArrayList<ItemRecommended>()
        imegList.add(ItemRecommended(R.drawable.traffic))
        return imegList
    }

    private fun initMoreTab(){

        val recycleMore= findViewById<RecyclerView>(R.id.moreRecyclerViewId) as RecyclerView
        recycleMore.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        val moreAdapter = RecomendedAdapter(imegList)
        recycleMore.adapter = moreAdapter
    }*/

}
