package com.pearldroidos.dessertshop.callback


import com.pearldroidos.dessertshop.model.DessertModel


/**
 * MainCallback interface is a callBack between main view and main presenter
 *
 * Created by Pearl DroidOs
 */
interface MainCallback {

    //Initialize view: passed list of 'DessertModel' variable
    fun initializeView(listDessert: ArrayList<DessertModel>)

    //Set action bar (Customize view)
    fun setActionBar()

    //Show progress bar when background working
    fun showProgressBar()

    //Hide progress bar when background finished
    fun hideProgressBar()

    //Show dialog with message and image by error type
    fun showDialog(message:String, image:Int)

    //Show refresh button when get internet error
    fun showRefreshView()

    //Hide refresh button when the system is stable
    fun hideRefreshView()

}