package com.aguadaserra.app.util

import android.view.View


interface RecyclerItemListener {

    fun onClickListenerItem(item: Any?){
        //optional body
    }

    fun onClickListenerItemWithPosition(item: Any?, position: Int){
        //optional body
    }

    fun onClickListenerItemWithType(v: View?, item: Any?, type: Int){
        //optional body
    }

    fun onLongClickListenerItem(v: View?, item: Any?){
        //optional body
    }

    fun onClickListenerPosition(position: Int){
        //optional body
    }

    fun actionListenerItem(item: Any?){
        //optional body
    }

}