package com.aguadaserra.app.main_ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aguadaserra.app.R
import com.aguadaserra.app.util.RecyclerItemListener
import com.google.android.material.imageview.ShapeableImageView

class CategoryAdapter(private val context: Context,
                      private val list: List<Any>,
                      private val onListener: RecyclerItemListener):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_FROM = 0
    private val VIEW_TYPE_TO = 1

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//
//        val nameTv: TextView = itemView.findViewById(R.id.name_tv)
//        val commentTv: TextView = itemView.findViewById(R.id.comment_tv)
//        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
//        val dateTv: TextView = itemView.findViewById(R.id.date_tv)
//        val numberNoReadMsgsTv: TextView = itemView.findViewById(R.id.numberMsgs_tv)
        val avatarIv: ShapeableImageView = itemView.findViewById(R.id.avatar_iv)

    }

    class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView){
        //
//        val nameTv: TextView = itemView.findViewById(R.id.name_tv)
//        val commentTv: TextView = itemView.findViewById(R.id.comment_tv)
//        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
//        val dateTv: TextView = itemView.findViewById(R.id.date_tv)
//        val numberNoReadMsgsTv: TextView = itemView.findViewById(R.id.numberMsgs_tv)
        val avatarIv: ShapeableImageView = itemView.findViewById(R.id.avatar_iv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var resource = 8
        var holder: RecyclerView.ViewHolder? = null
        val view: View

        when (viewType) {
            VIEW_TYPE_FROM -> {
                resource = R.layout.adapter_category_type_1
                view = LayoutInflater.from(parent.context).inflate(resource, parent, false)
                holder = ViewHolder(view)
            }


            VIEW_TYPE_TO -> {
                resource = R.layout.adapter_category_type_2
                view = LayoutInflater.from(parent.context).inflate(resource, parent, false)
                holder = ViewHolder2(view)
            }
        }

        return holder!!
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]

        when(position) {
            0 -> {

                holder as ViewHolder
                holder.avatarIv.setImageResource(R.drawable.randon6)
            }
            1 -> {

                holder as ViewHolder2
                holder.avatarIv.setImageResource(R.drawable.randon9)
            }
            2 -> {

                holder as ViewHolder
                holder.avatarIv.setImageResource(R.drawable.randon6)
            }
        }

        holder.itemView.setOnClickListener { onListener.onClickListenerItem(item) }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * The following method decides the type of ViewHolder to display in the RecyclerView
     *
     * @param position
     * @return
     */
    override fun getItemViewType(position: Int): Int {

        return if (position == 1) {

            VIEW_TYPE_TO
        } else {

            VIEW_TYPE_FROM

        }
    }


}