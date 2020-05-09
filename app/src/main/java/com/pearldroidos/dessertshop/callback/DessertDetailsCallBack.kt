package com.pearldroidos.dessertshop.callback

import com.pearldroidos.dessertshop.model.DessertModel


/**
 * DessertDetailsCallBack interface is a callBack between dessert details of view and dessert details of presenter
 *
 * Created by Pearl DroidOs
 */
interface DessertDetailsCallBack {

    //Initialize view: passed variable 'DessertModel' and Visibility of 'new' TextView
    fun initializeView(dessert:DessertModel, value:Int)

    //Set Action bar (Customize view)
    fun setActionBar()

}