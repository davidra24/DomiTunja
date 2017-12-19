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
        val imageDomicile = rowView.findViewById(R.id.imageDomicile) as ImageView
        val tituloDomicile = rowView.findViewById(R.id.txtTitleDomicile) as TextView
        val descripcionDomicile = rowView.findViewById(R.id.txtDescripDomicile) as TextView
        val imageStar = arrayListOf(rowView.findViewById(R.id.imgStarDomicile1) as ImageView,
                                    rowView.findViewById(R.id.imgStarDomicile2) as ImageView,
                                    rowView.findViewById(R.id.imgStarDomicile3) as ImageView,
                                    rowView.findViewById(R.id.imgStarDomicile4) as ImageView,
                                    rowView.findViewById(R.id.imgStarDomicile5) as ImageView)
        val btnViewDomicile = rowView.findViewById(R.id.btnViewDomicile) as ImageButton
        val item = this.items[position]
        imageDomicile.setImageResource(item.imagen)
        tituloDomicile.text = item.titulo
        descripcionDomicile.text = item.descripcion
        for (i in imageStar.indices){
            imageStar[i].setImageResource(item.stars[i])
        }
        btnViewDomicile.setImageResource(item.masInfo)
        return rowView
    }

}