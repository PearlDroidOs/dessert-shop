package com.pearldroidos.dessertshop.presenter

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.pearldroidos.dessertshop.R
import com.pearldroidos.dessertshop.callback.DessertDetailsCallBack
import com.pearldroidos.dessertshop.model.DessertModel
import com.pearldroidos.dessertshop.model.enum.GlobalEnum
import com.pearldroidos.dessertshop.presenter.utils.JsonUtils

/**
 * DessertDetailsPresenter is a presenter which informs and updates between view and service
 *
 * Use between 'DessertDetailsPresenter' class and 'DessertDetailsFragment' class
 *
 * Created by Pearl DroidOs
 */
class DessertDetailsPresenter(_callBack: DessertDetailsCallBack) {
    private val callBack = _callBack

    //Kept this information to easily handle in the future
    private lateinit var dessert: DessertModel

    //Receive bundle from main view (Unpacking data)
    fun getData(bundle: Bundle?) {
        var param: String? = null
        bundle?.let {
            param = it.getString(GlobalEnum.INFORMATION.name) //Unpack data by key
        }
        dessert = JsonUtils.covertJSONToObject(param!!, DessertModel::class.java) // Convert string data to 'DessertModel' class
    }

    //Set detail view
    fun setView(){
        //Use callback to contact view
        callBack.initializeView(dessert, if(dessert.isNewProduct) View.VISIBLE else View.GONE)
    }

    //Go to main view
    fun navigateToMainDesserts(view:View){
        view.findNavController().navigate(R.id.mainFragment)
    }

    //Set customized action bar
    fun setActionBar(){
        callBack.setActionBar()
    }


}