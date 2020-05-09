package com.pearldroidos.dessertshop.callback.interactor

import android.graphics.Bitmap
import com.pearldroidos.dessertshop.model.enum.ErrorEnum


/**
 * LoadImageListener interface is used for communicating between image service and main presenter
 *
 * if loading is successful, it will call updateImageData method (In case of empty data)
 * if Not, it will call error method
 *
 * Created by Pearl DroidOs
 */
interface LoadImageListener{

    //Update Image data and send list of bitmap to do a next process
    fun updateImageData(listOfBitmap:ArrayList<Bitmap>)

    //Send error out by error enum type
    fun error(error: ErrorEnum)
}