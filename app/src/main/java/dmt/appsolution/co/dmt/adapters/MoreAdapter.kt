package dmt.appsolution.co.dmt.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.itemList.ItemMore

class MoreAdapter(imageList: ArrayList<ItemMore>) : RecyclerView.Adapter<MoreAdapter.ViewHolderMore>() {
    var imageList = ArrayList<ItemMore>()

    init {
        this.imageList = imageList
    }

    override fun onBindViewHolder(holder: ViewHolderMore?, position: Int) {

        holder?.ima?.setImageResource(imageList.get(position).imeg)
    }

    override fun getItemCount(): Int {
        return  imageList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderMore {
        var aView = LayoutInflater.from(parent?.context).inflate(R.layout.more_list_layout, null,false)
        return ViewHolderMore(aView)
    }

    class ViewHolderMore(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ima = itemView.findViewById<ImageView>(R.id.imageMore) as ImageView
    }


}