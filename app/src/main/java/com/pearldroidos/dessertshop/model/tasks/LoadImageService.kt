package com.pearldroidos.dessertshop.model.tasks

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import com.pearldroidos.dessertshop.R
import com.pearldroidos.dessertshop.callback.interactor.LoadImageListener
import com.pearldroidos.dessertshop.model.enum.ErrorEnum
import java.io.InputStream
import java.net.URL
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

/**
 * LoadImageService is a background service which work on responsibility
 *
 * Mainly, responsibility is load image from url list (Business logic - Model)
 *
 * Created by Pearl DroidOs
 */
class LoadImageService(  private  val listener: LoadImageListener,
                         private  val context: Context) : AsyncTask<ArrayList<String>, String, ArrayList<Bitmap>>() {

    //A process which do in background
    override fun doInBackground(vararg params: ArrayList<String>?): ArrayList<Bitmap> {
        val listOfBitmap = ArrayList<Bitmap>() //Immutable Variable

        //Loop list of url image by params in zero index
        for (url in params[0]!!) {
            var bmp: Bitmap? //Mutable variable: Bitmap
            try {
                //Opn connection internet by url
                val urlConnect = URL(url).openConnection() as HttpsURLConnection

                //Check response code
                //200 is fine
                //Others will do in 'else' process
                if (urlConnect.responseCode == 200) {
                    val urlStream: InputStream = urlConnect.inputStream //Use stream input to download images
                    bmp = BitmapFactory.decodeStream(urlStream) //Decode from downloading images to Bitmap
                    listOfBitmap.add(bmp) //Save in to the list
                } else {
                    //Case 400 - Not found and another error case
                    //Save in to the list by using a default
                    listOfBitmap.add(
                        BitmapFactory.decodeResource(
                            context.resources,
                            R.mipmap.image_background
                        )
                    )
                }
            } catch (unKnownHost: UnknownHostException) {
                //Dialog internet error
                //return to 'onProgressUpdate' process to contact view
                publishProgress(ErrorEnum.INTERNET_ERROR.name)

                //Save stack
                unKnownHost.printStackTrace()
            } catch (ex: Exception) {
                //Dialog every error
                //return to 'onProgressUpdate' process to contact view
                publishProgress(ErrorEnum.INTERNET_ERROR.name)

                //Save stack
                ex.printStackTrace()
            }

        }
        //Return list of bitmap to onPostExecute() process
        return listOfBitmap
    }

    //During working on background and view
    override fun onProgressUpdate(vararg values: String?) {
        super.onProgressUpdate(*values)

        //Check condition error and return to presenter for contacting a view
        when {
            values[0] == ErrorEnum.INTERNET_ERROR.name -> {
                listener.error(ErrorEnum.INTERNET_ERROR) //Use listener to communicate with presenter
            }
            values[0] == ErrorEnum.ERROR.name -> {
                listener.error(ErrorEnum.ERROR) //Use listener to communicate with presenter
            }
        }
    }

    //Finished a process
    override fun onPostExecute(result: ArrayList<Bitmap>?) {
        super.onPostExecute(result)
        listener.updateImageData(result!!) //Use listener to communicate with presenter
    }


}