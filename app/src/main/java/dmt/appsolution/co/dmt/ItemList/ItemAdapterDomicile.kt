package dmt.appsolution.co.dmt.ItemList;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ItemAdapterDomicile(var context:Context, var items:List<ItemDomicile>) : BaseAdapter(){

    override fun getCount(): Int {
        return this.items.size
    }

    override fun getItem(position: Int): Any {
        return this.items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var rowView = convertView;
        if (convertView == null){
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = layoutInflater.inflate(R.layout.activity_item_paraderos, parent, false)
        }
        val imagenResultado = rowView.findViewById(R.id.imagenResultado) as ImageView
        val tituloResultado = rowView.findViewById(R.id.tituloResultado) as TextView
        val descripcionResultado = rowView.findViewById(R.id.descripcionResultado) as TextView
        val imageStar = rowView.findViewById(R.id.estrellasResultado) as ImageView
        val precioRestultado = rowView.findViewById(R.id.preciotext) as EditText
        val infobtn = rowView.findViewById(R.id.masinfobtn) as Button
        val item = this.items[position]
        imagenResultado.setImageResource(item.imagen)
        tituloResultado.text = item.titulo
        descripcionResultado.text = item.descripcion
        imageStar.setImageResource(item.stars)
        precioRestultado.setText(item.precio)
        infobtn.text = item.info
        return rowView
    }

}