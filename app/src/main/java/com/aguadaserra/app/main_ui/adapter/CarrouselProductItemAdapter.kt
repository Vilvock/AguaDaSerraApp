package com.aguadaserra.app.main_ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.aguadaserra.app.R
import com.aguadaserra.app.global_model.Photo

class CarrouselProductItemAdapter(private val list: List<Photo>):
    RecyclerView.Adapter<CarrouselProductItemAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val bannerIv: ImageView = itemView.findViewById(R.id.banner_iv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_carrousel_product_item, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, @SuppressLint("RecyclerView") position: Int) {


//        holder.bannerIv.setImageResource(R.drawable.banner_sir)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}