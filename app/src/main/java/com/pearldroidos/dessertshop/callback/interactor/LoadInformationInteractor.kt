package com.pearldroidos.dessertshop.callback.interactor

import com.pearldroidos.dessertshop.model.InformationModel
import com.pearldroidos.dessertshop.model.enum.ErrorEnum

interface LoadInformationInteractor {

    fun updateInformation(_listOfInformation:ArrayList<InformationModel>)

    fun error(error:ErrorEnum)

}