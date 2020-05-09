package com.pearldroidos.dessertshop.presenter

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.pearldroidos.dessertshop.R
import com.pearldroidos.dessertshop.callback.MainCallback
import com.pearldroidos.dessertshop.callback.interactor.LoadImageListener
import com.pearldroidos.dessertshop.callback.interactor.LoadInformationListener
import com.pearldroidos.dessertshop.model.DessertModel
import com.pearldroidos.dessertshop.model.InformationModel
import com.pearldroidos.dessertshop.model.enum.ErrorEnum
import com.pearldroidos.dessertshop.model.enum.GlobalEnum
import com.pearldroidos.dessertshop.model.tasks.LoadImageService
import com.pearldroidos.dessertshop.model.tasks.LoadInformationService
import com.pearldroidos.dessertshop.presenter.utils.JsonUtils

/**
 *  MainPresenter is a presenter which informs and updates between view and service
 *
 *  Use between 'MainPresenter' class and 'MainFragment' class
 *
 * Created by Pearl DroidOs
 */
class MainPresenter(
    private val callBack: MainCallback,
    private val context: Context
) : LoadInformationListener, LoadImageListener {

    //Kept these information to easily handle in the future
    private var listOfInformation = ArrayList<InformationModel>()
    private var listOfDessert = ArrayList<DessertModel>()

    //Set - Hide refresh view and show progress bar
    fun setView() {
        callBack.hideRefreshView()
        callBack.showProgressBar()
    }

    //Contact service to load data
    fun initData() {
        LoadInformationService(this).execute()
    }

    //Set customized action bar
    fun setActionBar() {
        callBack.setActionBar()
    }

    //Navigate to dessert detail page
    //Use dessertModel data and item view
    fun navigateToDessertDetail(dessertModel: DessertModel, view: View) {
        //Save data to send to the next view
        val bundle = Bundle()
        bundle.putString(GlobalEnum.INFORMATION.name, JsonUtils.covertObjectToJSON(dessertModel))

        //Use navigation of android x to navigate to dessert details page
        view.findNavController()
            .navigate(R.id.action_mainFragment_to_dessertDetailsFragment2, bundle)
    }

    //Convert InformationModel list and Bitmap list to DessertModel list
    private fun convertToDessertInfo(
        _listOfInformation: ArrayList<InformationModel>
        , _listOfBitmap: ArrayList<Bitmap>
    ): ArrayList<DessertModel> {

        val listOfDessert = ArrayList<DessertModel>() //Determine list of dessert
        var increment = 0.25 //Determine price by static increment

        //Loop to convert InformationModel list and Bitmap list to DessertModel
        for (info in _listOfInformation) {
            //Config dessert details
            val id: Int = info.id
            val name: String = info.name
            val index = _listOfInformation.indexOf(info)
            val image: Bitmap = _listOfBitmap[index]
            //Price start at 15.00 and increment every 0.30
            val price: Double = 15.00 + increment
            val description: String = info.description
            val isNewProduct: Boolean = info.isNewProduct

            //Add dessert
            listOfDessert.add(DessertModel(id, name, image, price, description, isNewProduct))

            increment += 0.30
        }
        return listOfDessert
    }

    //Convert list of dessert information to list of string url
    fun convertToImageUrlList(_listOfInformation: ArrayList<InformationModel>): ArrayList<String> {
        val listOfImageUrl = ArrayList<String>()
        for (info in _listOfInformation) {
                listOfImageUrl.add(info.image)
        }

        return listOfImageUrl
    }

    //Receive image data from image service
    override fun updateImageData(listOfBitmap: ArrayList<Bitmap>) {
        //Convert to dessert informatino
        this.listOfDessert = convertToDessertInfo(listOfInformation, listOfBitmap)

        //Set views
        callBack.hideProgressBar()
        callBack.hideRefreshView()
        callBack.initializeView(listOfDessert)
    }

    //Receive dessert information from information service
    override fun updateInformation(_listOfInformation: ArrayList<InformationModel>) {
        //Keep list of information to 'listOfInformation' variable
        this.listOfInformation = _listOfInformation

        //Convert to string url list
        val urlList = convertToImageUrlList(_listOfInformation)

        //Call service to load images
        LoadImageService(this, context).execute(urlList)
    }

    //Handle error from service by error type
    override fun error(error: ErrorEnum) {
        val image =
            if (error == ErrorEnum.INTERNET_ERROR) R.drawable.internet_error else R.drawable.error

        //Set view and call dialog by message and image types
        callBack.showRefreshView()
        callBack.showDialog(error.message, image)
    }

}