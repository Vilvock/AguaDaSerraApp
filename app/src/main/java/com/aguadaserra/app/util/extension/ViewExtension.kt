package com.aguadaserra.app.util.extension

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

fun View.navigateOnClick(directions: NavDirections) {
    setOnClickListener { findNavController().navigate(directions) }
}

fun View.navigateOnClick(destinationId: Int) {
    setOnClickListener { findNavController().navigate(destinationId) }
}