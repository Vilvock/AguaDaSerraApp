package com.aguadaserra.app.main_ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aguadaserra.app.R
import com.aguadaserra.app.util.RecyclerItemListener

class AddressAdapter(private val context: Context,
                     private val list: List<Any>,
                     private val onListener: RecyclerItemListener):
    RecyclerView.Adapter<AddressAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//
        val addressRb: RadioButton = itemView.findViewById(R.id.address_rb)
//        val commentTv: TextView = itemView.findViewById(R.id.comment_tv)
//        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
//        val dateTv: TextView = itemView.findViewById(R.id.date_tv)
//        val numberNoReadMsgsTv: TextView = itemView.findViewById(R.id.numberMsgs_tv)
//        val avatarIv: ShapeableImageView = itemView.findViewById(R.id.avatar_iv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_user_address, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.addressRb.visibility = View.VISIBLE


    }

    override fun getItemCount(): Int {
        return list.size
    }


}