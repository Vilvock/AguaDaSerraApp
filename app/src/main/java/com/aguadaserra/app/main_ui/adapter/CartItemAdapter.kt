package com.aguadaserra.app.main_ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aguadaserra.app.R
import com.aguadaserra.app.util.RecyclerItemListener

class CartItemAdapter(private val context: Context,
                      private val list: List<Any>,
                      private val onListener: RecyclerItemListener):
    RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//
//        val nameTv: TextView = itemView.findViewById(R.id.name_tv)
//        val commentTv: TextView = itemView.findViewById(R.id.comment_tv)
//        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
//        val dateTv: TextView = itemView.findViewById(R.id.date_tv)
//        val numberNoReadMsgsTv: TextView = itemView.findViewById(R.id.numberMsgs_tv)
//        val avatarIv: ShapeableImageView = itemView.findViewById(R.id.avatar_iv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_cart_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]


        holder.itemView.setOnClickListener { onListener.onClickListenerItem(item) }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}