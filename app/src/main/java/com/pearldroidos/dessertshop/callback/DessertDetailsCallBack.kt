package com.pearldroidos.dessertshop.callback

import com.pearldroidos.dessertshop.model.DessertModel

interface DessertDetailsCallBack {

    fun initializeView(dessert:DessertModel, value:Int)
    fun setActionBar(title:String, backButtonVisible:Int)

}