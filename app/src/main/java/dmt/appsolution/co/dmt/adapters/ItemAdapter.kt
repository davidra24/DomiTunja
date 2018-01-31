package dmt.appsolution.co.dmt.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.services.entity.Lugar
import com.bumptech.glide.Glide
import dmt.appsolution.co.dmt.activities.LoadActivity
import dmt.appsolution.co.dmt.activities.RestaurantActivity
import dmt.appsolution.co.dmt.constants.Constants
import dmt.appsolution.co.dmt.services.consumeRest.DetailsRest


class ItemAdapter(var context:Context, items:List<Lugar>) : BaseAdapter(){
    private var itemsRestaurant: List<Lugar>? = null

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

        var rowView: View? = convertView
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        rowView = layoutInflater.inflate(R.layout.activity_domicile_item, null)
        val imageDomicile = rowView.findViewById<ImageView>(R.id.imageDomicile)
        val titleDomicile = rowView.findViewById<TextView>(R.id.txtTitleDomicile)
        val descriptionDomicile = rowView.findViewById<TextView>(R.id.txtDescripDomicile)
        val ratingBar = rowView.findViewById<com.iarcuschin.simpleratingbar.SimpleRatingBar>(R.id.ratingBarDomicile)
        val btnViewDomicile = rowView.findViewById<ImageButton>(R.id.btnViewDomicile)
        val item = this.itemsRestaurant!![position]
        if(item.idLugar != null) {
            Glide.with(context).load(Constants.REST_URL + "photos/" + item.url).into(imageDomicile)
            titleDomicile.text = item.nombre
            descriptionDomicile.text = item.direccion
            ratingBar.rating = item.calificacion!!.toFloat()
            btnViewDomicile.setOnClickListener {
            Constants.ID_LUGAR = item.idLugar!!
            val bundle = Bundle()
            bundle.putSerializable("Item", item)
            val intent = Intent(context, LoadActivity::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
            }
        }else {
            imageDomicile.visibility = View.INVISIBLE
            titleDomicile.visibility = View.GONE
            descriptionDomicile.visibility = View.GONE
            btnViewDomicile.visibility = View.GONE
            ratingBar.visibility = View.GONE
        }
        return rowView
    }
}