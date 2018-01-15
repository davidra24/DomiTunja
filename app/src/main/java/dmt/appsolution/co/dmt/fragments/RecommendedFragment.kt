package dmt.appsolution.co.dmt.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.adapters.ItemAdapter
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
        val items: MutableList<Lugar> = mutableListOf()
        items.add(Lugar(1,"pollo@gmail.com", "Restaurante de pollo", "Las Nieves", 3.5,
                3, "313131313", "300333333", "Calle 24 # 6 -124", "www.labrasaroja.com/",
                "hola K ase", 1, "persona@gmail.com", 34, "Restaurante Pollo",
                "Matricula ", 1, 5.532392303876586, -73.36294144392014))
        items.add(Lugar(2,"pollo@gmail.com", "Restaurante de carne", "Las Nieves", 3.5,
                3, "313131313", "300333333", "Calle 24 # 6 -124", "www.labrasaroja.com/",
                "hola K ase", 2, "persona@gmail.com", 34, "Restaurante Pollo",
                "Matricula ", 1, 5.532213432951909, -73.36247071623802))
        items.add(Lugar(3,"pollo@gmail.com", "Restaurante de pescado", "Las Nieves", 3.5,
                3, "313131313", "300333333", "Calle 24 # 6 -124", "www.labrasaroja.com/",
                "hola K ase", 3, "persona@gmail.com", 34, "Restaurante Pollo",
                "Matricula ", 1, 5.5322694969788975, -73.36216159164906))
        items.add(Lugar(1,"pollo@gmail.com", "Restaurante de pollo", "Las Nieves", 3.5,
                3, "313131313", "300333333", "Calle 24 # 6 -124", "www.labrasaroja.com/",
                "hola K ase", 1, "persona@gmail.com", 34, "Restaurante Pollo",
                "Matricula ", 1, 5.532392303876586, -73.36294144392014))
        items.add(Lugar(2,"pollo@gmail.com", "Restaurante de carne", "Las Nieves", 3.5,
                3, "313131313", "300333333", "Calle 24 # 6 -124", "www.labrasaroja.com/",
                "hola K ase", 2, "persona@gmail.com", 34, "Restaurante Pollo",
                "Matricula ", 1, 5.532213432951909, -73.36247071623802))
        items.add(Lugar(3,"pollo@gmail.com", "Restaurante de pescado", "Las Nieves", 3.5,
                3, "313131313", "300333333", "Calle 24 # 6 -124", "www.labrasaroja.com/",
                "hola K ase", 3, "persona@gmail.com", 34, "Restaurante Pollo",
                "Matricula ", 1, 5.5322694969788975, -73.36216159164906))
        listViewRecommended.adapter = ItemAdapter(this.activity, items)
    }
}
