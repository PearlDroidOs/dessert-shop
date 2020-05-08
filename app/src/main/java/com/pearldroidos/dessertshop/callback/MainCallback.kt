package com.pearldroidos.dessertshop.callback

import android.graphics.drawable.Drawable
import com.pearldroidos.dessertshop.model.DessertModel

interface MainCallback {

    fun initializeView(listDessert:ArrayList<DessertModel>)
    fun setActionBar(title:String, backButtonVisible:Int)
    fun setProgressBar(value:Int)


    fun showDialog(message:String, image:Int)
    fun showRefresh(value: Int)

}