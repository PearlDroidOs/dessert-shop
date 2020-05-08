package com.pearldroidos.dessertshop.presenter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.navigation.findNavController
import com.pearldroidos.dessertshop.R
import com.pearldroidos.dessertshop.callback.DessertDetailsCallBack
import com.pearldroidos.dessertshop.model.DessertModel
import com.pearldroidos.dessertshop.model.enum.GlobalEnum
import com.pearldroidos.dessertshop.presenter.utils.JsonUtils

class DessertDetailsPresenter(_callBack: DessertDetailsCallBack,_context: Context) {
    private val callBack = _callBack
    private val context =_context
    private var param: String? = null
    private lateinit var dessert: DessertModel


    fun setView(bundle: Bundle?) {
        bundle?.let {
            param = it.getString(GlobalEnum.INFORMATION.name)
        }
        dessert = JsonUtils.covertJSONToObject(param!!, DessertModel::class.java)

        Log.d("Test", " JSON Details Fragment: $param")
        Log.d("Test", " Class Details Fragment : $dessert")

        val visibility = if (dessert.isNewProduct) View.VISIBLE else View.GONE
        callBack.initializeView(dessert, visibility)
    }

    fun navigateToMainDesserts(view:View){
        view.findNavController().navigate(R.id.mainFragment)
    }

    fun setActionBar(){
        val title = context.getString(R.string.product_title)
        callBack.setActionBar(title, View.VISIBLE)
    }


}