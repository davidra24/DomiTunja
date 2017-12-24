package dmt.appsolution.co.dmt.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.activities.RestaurantActivity
import dmt.appsolution.co.dmt.itemList.ItemRestaurant

class ItemAdapter(var context:Context, private var  items:List<ItemRestaurant>) : BaseAdapter(){
    var itemsRestaurant: List<ItemRestaurant>? = null

    init {
        this.itemsRestaurant = items
    }

    override fun getCount(): Int {
        return this.itemsRestaurant!!.size
    }

    override fun getItem(position: Int): Any {
        return this.itemsRestaurant!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("InflateParams", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var rowView = convertView
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        rowView = layoutInflater.inflate(R.layout.activity_domicile_item, null)
        val imageDomicile = rowView.findViewById<ImageView>(R.id.imageDomicile)
        val titleDomicile = rowView.findViewById<TextView>(R.id.txtTitleDomicile)
        val descriptionDomicile = rowView.findViewById<TextView>(R.id.txtDescripDomicile)
        val ratingBar = rowView.findViewById<RatingBar>(R.id.ratingBarDomicile)
        val btnViewDomicile = rowView.findViewById<ImageButton>(R.id.btnViewDomicile)
        val item = this.itemsRestaurant!![position]
        imageDomicile.setImageResource(item.imagen)
        titleDomicile.text = item.titulo
        descriptionDomicile.text = item.descripcion
        ratingBar.rating = item.rating.toFloat()
        btnViewDomicile.setImageResource(item.masInfo)
        btnViewDomicile.setOnClickListener{
            context.startActivity(Intent(context, RestaurantActivity::class.java))
        }
        return rowView
    }

}