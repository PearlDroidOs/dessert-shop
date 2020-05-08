package com.pearldroidos.dessertshop.presenter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.navigation.findNavController
import com.pearldroidos.dessertshop.R
import com.pearldroidos.dessertshop.callback.MainCallback
import com.pearldroidos.dessertshop.callback.interactor.LoadImageInteractor
import com.pearldroidos.dessertshop.callback.interactor.LoadInformationInteractor
import com.pearldroidos.dessertshop.model.DessertModel
import com.pearldroidos.dessertshop.model.InformationModel
import com.pearldroidos.dessertshop.model.enum.ErrorEnum
import com.pearldroidos.dessertshop.model.enum.GlobalEnum
import com.pearldroidos.dessertshop.model.tasks.LoadImageTask
import com.pearldroidos.dessertshop.model.tasks.LoadInformationTask
import com.pearldroidos.dessertshop.presenter.utils.JsonUtils

class MainPresenter(_callBack: MainCallback, _context: Context) : LoadInformationInteractor, LoadImageInteractor {
    private var callBack = _callBack
    private val context = _context

    //Kept these information to easily handle in the future
    private var listOfInformation = ArrayList<InformationModel>()
    private var listOfDessert = ArrayList<DessertModel>()


    fun setView() {
        Log.d("Test", "setView")
        callBack.showRefresh(View.GONE)
        callBack.setProgressBar(View.VISIBLE)
        LoadInformationTask(this).execute()
    }

    fun setActionBar(){
        val title = context.getString(R.string.product_title)
        callBack.setActionBar(title, View.GONE)
    }

    fun navigateToDessertDetail(dessertModel: DessertModel, view: View){
        val bundle = Bundle()
        bundle.putString(GlobalEnum.INFORMATION.name, JsonUtils.covertObjectToJSON(dessertModel))

        Log.d("Test", "navigateToDessertDetail: ${dessertModel.toString()}")

        view.findNavController().navigate(R.id.action_mainFragment_to_dessertDetailsFragment2, bundle)
    }

    private fun convertToDessertInfo(_listOfInformation: ArrayList<InformationModel>
                                     , _listOfBitmap: ArrayList<Bitmap>): ArrayList<DessertModel> {
        val listOfDessert = ArrayList<DessertModel>()
        var increment = 0.25

        for (info in _listOfInformation) {
            val id: Int = info.id
            val name: String = info.name

            val index = _listOfInformation.indexOf(info)
            var image: Bitmap?
            image = if(_listOfBitmap[index] ==  null){
                BitmapFactory.decodeResource(context.resources,
                    R.mipmap.image_background)
            }else{
                _listOfBitmap[_listOfInformation.indexOf(info)]
            }

            val price: Double = 15.00 + increment
            val description: String = info.description
            val isNewProduct: Boolean = info.isNewProduct
            listOfDessert.add(DessertModel(id, name, image!!, price, description, isNewProduct))

            increment += 0.30
        }
        return listOfDessert
    }
    
    private fun convertToImageUrlList(_listOfInformation: ArrayList<InformationModel>): ArrayList<String>{
        val listOfImageUrl = ArrayList<String>()
        for(info in _listOfInformation){
            listOfImageUrl.add(info.image)
        }

        return listOfImageUrl
    }

    override fun updateImageData(listOfBitmap: ArrayList<Bitmap>) {
        listOfDessert = convertToDessertInfo(listOfInformation, listOfBitmap)
        callBack.setProgressBar(View.GONE)
        callBack.showRefresh(View.GONE)
        callBack.initializeView(listOfDessert)
    }

    override fun updateInformation(_listOfInformation: ArrayList<InformationModel>) {
        if(_listOfInformation.isNotEmpty()) {
            Log.d("Test", "_listOfInformation is not empty")
            listOfInformation = _listOfInformation
            LoadImageTask(this, context).execute(convertToImageUrlList(_listOfInformation))
        }else{
            callBack.setProgressBar(View.GONE)
        }
    }

    override fun error(error: ErrorEnum) {
        Log.d("Test", "call error")

        val image = if(error == ErrorEnum.INTERNET_ERROR) R.drawable.internet_error else R.drawable.error
        callBack.showRefresh(View.VISIBLE)
        callBack.showDialog(error.message, image)
    }


}