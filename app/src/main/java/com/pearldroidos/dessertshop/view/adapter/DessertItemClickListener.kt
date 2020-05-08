package com.pearldroidos.dessertshop.view.adapter

import android.view.View

interface DessertItemClickListener {
    fun onItemClick(position: Int, view: View)
}