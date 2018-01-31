package dmt.appsolution.co.dmt.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.adapters.ItemAdapter
import dmt.appsolution.co.dmt.constants.Constants
import dmt.appsolution.co.dmt.services.entity.Lugar
import kotlinx.android.synthetic.main.fragment_recomended.*


class RecommendedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_recomended, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fillListRecommended()
    }

    private fun fillListRecommended(){
        val items: MutableList<Lugar> = Constants.restaurantList
                /*.filter { it.idLugar != null && it.habilitado!! }
                .toMutableList()*/
        listViewRecommended.adapter = ItemAdapter(this.activity, items)
    }

    override fun onResume() {
        fillListRecommended()
        super.onResume()
    }
}
