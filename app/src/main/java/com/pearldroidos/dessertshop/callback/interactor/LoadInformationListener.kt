package com.pearldroidos.dessertshop.callback.interactor

import com.pearldroidos.dessertshop.model.InformationModel
import com.pearldroidos.dessertshop.model.enum.ErrorEnum


/**
 * LoadInformationListener is used for communicating between information service and main presenter
 *
 * if loading is successful, it will call updateImageData method (In case of empty data)
 * if Not, it will call error method
 *
 * Created by Pearl DroidOs
 */
interface LoadInformationListener {

    //Update loading information and send them out by array list of information
    fun updateInformation(_listOfInformation:ArrayList<InformationModel>)

    //Send error out by error enum type
    fun error(error:ErrorEnum)

}