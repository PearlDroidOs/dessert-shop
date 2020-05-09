package com.pearldroidos.dessertshop.view.adapter

import android.view.View

/**
 * Listener between adapter and view holder
 *
 * Created by Pearl DroidOs
 */
interface DessertItemClickListener {

    //Handle item click by this method
    fun onItemClick(position: Int, view: View)
}